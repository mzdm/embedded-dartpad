package com.github.mzdm.embedded_dartpad.components

import com.intellij.ui.layout.panel
import javax.swing.JPanel

fun statusMessage(message: String): JPanel {
    return panel {
        noteRow(message)
    }
}
