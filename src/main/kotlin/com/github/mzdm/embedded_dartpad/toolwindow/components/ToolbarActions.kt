package com.github.mzdm.embedded_dartpad.toolwindow.components

import com.github.mzdm.embedded_dartpad.app.constants.Icons
import com.github.mzdm.embedded_dartpad.dartpad.services.PadSettingsService
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import javax.swing.JComponent

fun toolbarActions(project: Project, onRefresh: () -> Unit): JComponent {
    val actionGroup = DefaultActionGroup()

    actionGroup.addAll(
        ToolbarIconAction(
            "Refresh",
            "Reloads the current page",
            AllIcons.Actions.Refresh,
            onAction = onRefresh,
        ),
        ToolbarPadToggleAction(
            project,
            "Flutter environment",
            null,
            IconLoader.getIcon(Icons.flutter_logo),
            onToggled = onRefresh,
        ),
        ToolbarThemeToggleAction(
            project,
            "Dark embed theme",
            null,
            IconLoader.getIcon(Icons.theme),
            onToggled = onRefresh,
        ),
        ToolbarTextIconAction(
            "Reset", "Resets everything to default", null,
            onAction = {
                project.service<PadSettingsService>()
                    .reset()
                onRefresh()
            },
        ),
    )

    val actionManager = ActionManager.getInstance()
    val actionToolBar = actionManager.createActionToolbar(ActionPlaces.TOOLBAR, actionGroup, true)
    return actionToolBar.component
}
