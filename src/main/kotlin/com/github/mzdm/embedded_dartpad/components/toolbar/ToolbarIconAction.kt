package com.github.mzdm.embedded_dartpad.components.toolbar

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import javax.swing.Icon

class ToolbarIconAction(text: String, desc: String, icon: Icon?, val onAction: () -> Unit) :
    AnAction(text, desc, icon) {

    override fun actionPerformed(e: AnActionEvent) {
        onAction()
    }
}
