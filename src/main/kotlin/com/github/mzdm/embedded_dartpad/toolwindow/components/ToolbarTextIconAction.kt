package com.github.mzdm.embedded_dartpad.toolwindow.components

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.roots.ui.configuration.actions.IconWithTextAction
import javax.swing.Icon

class ToolbarTextIconAction(text: String, desc: String, icon: Icon?, val onAction: () -> Unit) :
    IconWithTextAction(text, desc, icon) {

    override fun actionPerformed(e: AnActionEvent) {
        onAction()
    }
}
