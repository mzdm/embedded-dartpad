package com.github.mzdm.embedded_dartpad.toolwindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory

class DartPadWindowFactory : ToolWindowFactory {

    companion object {
        const val id = " DartPad"
        val instance = DartPadWindowFactory()
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentManager = toolWindow.contentManager
        val content = contentManager.factory.createContent(DartPadWindowPanel(project), null, true)
        contentManager.addContent(content)
        toolWindow.show()
    }
}
