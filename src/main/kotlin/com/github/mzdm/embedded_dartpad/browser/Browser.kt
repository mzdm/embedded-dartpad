package com.github.mzdm.embedded_dartpad.browser

import com.github.mzdm.embedded_dartpad.browser.utils.HtmlContentRenderer
import com.github.mzdm.embedded_dartpad.dartpad.models.PadSettings
import com.intellij.ui.jcef.JBCefBrowser

class Browser : JBCefBrowser() {
    fun refresh(settings: PadSettings) {
        val html = HtmlContentRenderer.load(settings)
        this.loadHTML(html)
    }
}
