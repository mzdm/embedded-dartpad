package com.github.mzdm.embedded_dartpad.components.toolbar

import com.github.mzdm.embedded_dartpad.models.Pad
import com.github.mzdm.embedded_dartpad.services.SettingsService
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import javax.swing.Icon

class PadToggleAction(
    private val project: Project, text: String, desc: String?, icon: Icon?, val onSelected: () -> Unit,
) : ToggleAction(text, desc, icon) {

    private val settingsService: SettingsService
        get() = project.service()
    private val currentPad: Pad
        get() = settingsService.settings.pad

    override fun isSelected(e: AnActionEvent): Boolean {
        return currentPad == Pad.FLUTTER
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        if (currentPad == Pad.FLUTTER) {
            settingsService.setPad(Pad.DART)
        } else {
            settingsService.setPad(Pad.FLUTTER)
        }
        onSelected()
    }
}
