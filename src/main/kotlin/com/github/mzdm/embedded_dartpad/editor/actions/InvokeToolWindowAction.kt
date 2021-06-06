package com.github.mzdm.embedded_dartpad.editor.actions

import com.github.mzdm.embedded_dartpad.dartpad.models.FlutterTemplate
import com.github.mzdm.embedded_dartpad.dartpad.models.Pad
import com.github.mzdm.embedded_dartpad.dartpad.models.PadSettings
import com.github.mzdm.embedded_dartpad.dartpad.services.PadSettingsService
import com.github.mzdm.embedded_dartpad.toolwindow.DartPadWindowFactory
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager

class InvokeToolWindowAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project: Project? = event.project

        if (project != null) {
            val editor = event.getData(CommonDataKeys.EDITOR)
            val toolbarWindow = ToolWindowManager.getInstance(project)
                .getToolWindow(DartPadWindowFactory.id)

            if (editor != null && toolbarWindow != null) {
                val settingsService = project.service<PadSettingsService>()

                val selectedText = editor.selectionModel.getSelectedText(true)
                settingsService.setWidget(selectedText)

                val flutterTemplate = getTemplateByAction(project, event.presentation.text, selectedText)
                settingsService.setTemplate(flutterTemplate)

                createToolWindowContent(toolbarWindow, project)
                toolbarWindow.show()
            }
        }
    }

    private fun getTemplateByAction(project: Project, text: String, selectedText: String?): FlutterTemplate {
        val settingsService = project.service<PadSettingsService>()
        val padSettings: PadSettings = settingsService.settings

        return when (text) {
            "Flutter (Stateful)" -> {
                settingsService.setPad(Pad.FLUTTER)
                FlutterTemplate.Stateful(
                    padSettings.theme,
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
    }

    private fun createToolWindowContent(toolbarWindow: ToolWindow, project: Project) {
        val contentManager = toolbarWindow.contentManager
        val content = contentManager.getContent(0)
        if (content != null) {
            contentManager.removeContent(content, true)
            DartPadWindowFactory.instance.createToolWindowContent(project, toolbarWindow)
        }
    }

    override fun update(e: AnActionEvent) {
        val project: Project? = e.project
        e.presentation.isEnabledAndVisible = project != null
    }
}

