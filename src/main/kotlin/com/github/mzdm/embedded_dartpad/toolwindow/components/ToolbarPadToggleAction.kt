package com.github.mzdm.embedded_dartpad.toolwindow.components

import com.github.mzdm.embedded_dartpad.dartpad.models.Pad
import com.github.mzdm.embedded_dartpad.dartpad.services.PadSettingsService
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import javax.swing.Icon

class ToolbarPadToggleAction(
    private val project: Project, text: String, desc: String?, icon: Icon?, val onToggled: () -> Unit,
) : ToggleAction(text, desc, icon) {

    private val padSettingsService: PadSettingsService
        get() = project.service()
    private val currentPad: Pad
        get() = padSettingsService.settings.pad

    override fun isSelected(e: AnActionEvent): Boolean {
        return currentPad == Pad.FLUTTER
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        if (currentPad == Pad.FLUTTER) {
            padSettingsService.setPad(Pad.DART)
        } else {
            padSettingsService.setPad(Pad.FLUTTER)
        }
        onToggled()
    }
}
