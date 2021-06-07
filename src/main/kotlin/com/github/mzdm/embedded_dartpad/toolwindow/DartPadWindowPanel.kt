package com.github.mzdm.embedded_dartpad.toolwindow

import com.github.mzdm.embedded_dartpad.app.helpers.ComponentList
import com.github.mzdm.embedded_dartpad.app.helpers.addAll
import com.github.mzdm.embedded_dartpad.browser.Browser
import com.github.mzdm.embedded_dartpad.dartpad.models.PadSettings
import com.github.mzdm.embedded_dartpad.dartpad.services.PadSettingsService
import com.github.mzdm.embedded_dartpad.toolwindow.components.toolbarActions
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.layout.panel
import javax.swing.BoxLayout
import javax.swing.SwingUtilities

class DartPadWindowPanel(private val project: Project) : SimpleToolWindowPanel(false) {

    private val padSettingsService: PadSettingsService
        get() = project.service()
    private val settings: PadSettings
        get() = padSettingsService.settings

    init {
        // JBCef browser throws an error on Android Studio
        val isJcefSupported: Boolean = try {
            JBCefApp.isSupported()
        } catch (t: Throwable) {
            false
        }

        if (isJcefSupported) {
            SwingUtilities.invokeLater {
                initJbCefBrowser()
            }
        } else {
            showUnsupportedMessage()
        }
    }

    private fun initJbCefBrowser() {
        val browser = Browser()
        browser.refresh(settings)

        val verticalLayout = BoxLayout(this, BoxLayout.Y_AXIS)
        layout = verticalLayout

        addAll(
            ComponentList.of(
                toolbarActions(
                    project,
                    onRefresh = { browser.refresh(settings) },
                ),
                browser.component,
            ),
        )

        validate()
        repaint()
    }

    private fun showUnsupportedMessage() {
        add(panel {
            row {
                row {
                    label("You're probably on Android Studio.\nEmbedded DartPad plugin is not supported in this IDE.")
                }
                row {
                    link("Read more for a workaround (#Troubleshooting section) ...") {
                        BrowserUtil.browse("https://github.com/mzdm/embedded-dartpad#troubleshooting")
                    }
                }
            }
        })
    }
}
