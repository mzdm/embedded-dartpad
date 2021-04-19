package com.github.mzdm.embedded_dartpad.components.toolbar

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.roots.ui.configuration.actions.IconWithTextAction
import javax.swing.Icon

class ToolbarTextAction(text: String, desc: String, icon: Icon?, val onAction: () -> Unit) :
    IconWithTextAction(text, desc, icon) {

    override fun actionPerformed(e: AnActionEvent) {
        onAction()
    }
}
