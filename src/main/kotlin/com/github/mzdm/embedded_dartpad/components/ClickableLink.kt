package com.github.mzdm.embedded_dartpad.components

import com.intellij.ide.BrowserUtil
import com.intellij.ui.layout.panel
import javax.swing.JPanel

fun clickableLink(label: String, url: String): JPanel {
    return panel {
        noteRow("<a href=''>$label</a>") {
            BrowserUtil.browse(url)
        }
    }
}
