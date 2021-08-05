package com.github.mzdm.embedded_dartpad.toolwindow.components

import com.github.mzdm.embedded_dartpad.dartpad.models.Theme
import com.github.mzdm.embedded_dartpad.dartpad.services.PadSettingsService
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import javax.swing.Icon

class ToolbarThemeToggleAction(
    private val project: Project, text: String, desc: String?, icon: Icon?, val onToggled: () -> Unit,
) : ToggleAction(text, desc, icon) {

    private val padSettingsService: PadSettingsService
        get() = project.service()
    private val currentTheme: Theme
        get() = padSettingsService.settings.theme

    override fun isSelected(e: AnActionEvent): Boolean {
        return currentTheme == Theme.DARK
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        if (currentTheme == Theme.DARK) {
            padSettingsService.setTheme(Theme.LIGHT)
        } else {
            padSettingsService.setTheme(Theme.DARK)
        }
        onToggled()
    }
}
