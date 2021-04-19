package com.github.mzdm.embedded_dartpad.toolwindow

import com.github.mzdm.embedded_dartpad.browser.Browser
import com.github.mzdm.embedded_dartpad.components.ComponentPanel
import com.github.mzdm.embedded_dartpad.components.clickableLink
import com.github.mzdm.embedded_dartpad.components.statusMessage
import com.github.mzdm.embedded_dartpad.components.toolbar.toolbarActions
import com.github.mzdm.embedded_dartpad.models.DartPadSettings
import com.github.mzdm.embedded_dartpad.services.SettingsService
import com.github.mzdm.embedded_dartpad.utils.HtmlContentRenderer
import com.github.mzdm.embedded_dartpad.utils.addAll
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.jcef.JBCefApp
import javax.swing.BoxLayout
import javax.swing.SwingUtilities

class DartPadWindow(private val project: Project) : SimpleToolWindowPanel(false) {
    private val browser = Browser()

    private val settingsService: SettingsService
        get() = project.service()
    private val settings: DartPadSettings
        get() = settingsService.settings

    init {
        if (!JBCefApp.isSupported()) {
            showUnsupportedMessage()
        } else {
            SwingUtilities.invokeLater {
                initBrowserToolWindow()
            }
        }
    }

    private fun initBrowserToolWindow() {
        refresh()

        val verticalLayout = BoxLayout(this, BoxLayout.Y_AXIS)
        layout = verticalLayout

        addAll(
            ComponentPanel.of(
                toolbarActions(
                    project,
                    onRefresh = { refresh() },
                ),
                browser.component,
            ),
        )

        validate()
        repaint()
    }

    private fun refresh() {
        browser.loadHTML(HtmlContentRenderer.load(settings))
    }

    private fun showUnsupportedMessage() {
        addAll(
            ComponentPanel.of(
                statusMessage("Dart Pad is not supported in this IDE environment."),
                clickableLink("Read more ...", "https://www.google.com/"),
            ),
        )
    }
}
