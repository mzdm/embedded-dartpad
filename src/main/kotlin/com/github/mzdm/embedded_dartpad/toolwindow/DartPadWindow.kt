package com.github.mzdm.embedded_dartpad.toolwindow

import com.github.mzdm.embedded_dartpad.browser.Browser
import com.github.mzdm.embedded_dartpad.components.ComponentPanel
import com.github.mzdm.embedded_dartpad.components.toolbar.toolbarActions
import com.github.mzdm.embedded_dartpad.models.DartPadSettings
import com.github.mzdm.embedded_dartpad.services.SettingsService
import com.github.mzdm.embedded_dartpad.utils.HtmlContentRenderer
import com.github.mzdm.embedded_dartpad.utils.addAll
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.layout.panel
import javax.swing.BoxLayout
import javax.swing.SwingUtilities

class DartPadWindow(private val project: Project) : SimpleToolWindowPanel(false) {

    private val settingsService: SettingsService
        get() = project.service()
    private val settings: DartPadSettings
        get() = settingsService.settings

    init {
        val isJcefSupported: Boolean = try {
            JBCefApp.isSupported()
        } catch (t: Throwable) {
            false
        }

        if (!isJcefSupported) {
            showUnsupportedMessage()
        } else {
            SwingUtilities.invokeLater {
                initBrowserToolWindow()
            }
        }
    }

    private fun initBrowserToolWindow() {
        val browser = Browser()
        refresh(browser)

        val verticalLayout = BoxLayout(this, BoxLayout.Y_AXIS)
        layout = verticalLayout

        addAll(
            ComponentPanel.of(
                toolbarActions(
                    project,
                    onRefresh = { refresh(browser) },
                ),
                browser.component,
            ),
        )

        validate()
        repaint()
    }

    private fun refresh(browser: Browser) {
        browser.loadHTML(HtmlContentRenderer.load(settings))
    }

    private fun showUnsupportedMessage() {
        add(panel {
            row {
                row {
                    label("Embedded DartPad is not supported in this IDE version.")
                }
                row {
                    link("Read more ...") {
                        BrowserUtil.browse("https://github.com/mzdm/embedded-dartpad#troubleshooting")
                    }
                }
            }
        })
    }
}
