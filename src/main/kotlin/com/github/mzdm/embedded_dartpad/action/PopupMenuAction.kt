package com.github.mzdm.embedded_dartpad.action

import com.github.mzdm.embedded_dartpad.models.DartPadSettings
import com.github.mzdm.embedded_dartpad.models.FlutterTemplate
import com.github.mzdm.embedded_dartpad.models.Pad
import com.github.mzdm.embedded_dartpad.services.SettingsService
import com.github.mzdm.embedded_dartpad.toolwindow.DartPadWindowFactory
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindowManager
import javax.swing.Icon

class PopupMenuAction : AnAction {
    constructor() : super()

    constructor(text: String?, description: String?, icon: Icon?) : super(text, description, icon)

    override fun actionPerformed(event: AnActionEvent) {
        val project: Project? = event.project

        if (project != null) {
            val editor = event.getData(CommonDataKeys.EDITOR)

            val toolbarWindow = ToolWindowManager.getInstance(project)
                .getToolWindow(DartPadWindowFactory.id)

            if (editor != null && toolbarWindow != null) {

                val settingsService = project.service<SettingsService>()
                val dartPadSettings: DartPadSettings = settingsService.settings

                val selectedText = editor.selectionModel.getSelectedText(true)
                settingsService.setWidget(selectedText)

                val flutterTemplate: FlutterTemplate = when (event.presentation.text) {
                    "Flutter (Stateful)" -> {
                        settingsService.setPad(Pad.FLUTTER)
                        FlutterTemplate.Stateful(
                            dartPadSettings.theme,
                            selectedText,
                        )
                    }
                    "Dart" -> {
                        settingsService.setPad(Pad.DART)
                        FlutterTemplate.None(selectedText)
                    }
                    else -> {
                        settingsService.setPad(Pad.FLUTTER)
                        FlutterTemplate.None(selectedText)
                    }
                }
                settingsService.setTemplate(flutterTemplate)

                if (!toolbarWindow.isActive) {
                    val contentManager = toolbarWindow.contentManager
                    val content = contentManager.getContent(0)
                    if (content != null) {
                        contentManager.removeContent(content, true)
                        DartPadWindowFactory.instance.createToolWindowContent(project, toolbarWindow)
                    }
                }

                toolbarWindow.show()
            }
        }
    }

    override fun update(e: AnActionEvent) {
        val project: Project? = e.project
        e.presentation.isEnabledAndVisible = project != null
    }
}
