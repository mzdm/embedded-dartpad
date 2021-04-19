package com.github.mzdm.embedded_dartpad.components

import com.intellij.ui.layout.Cell
import java.awt.Color
import javax.swing.JComponent

fun Cell.expandedX() : JComponent {
    return button("") { }.constraints(pushX, growX).component.apply {
        isOpaque = false
        isBorderPainted = false
        foreground = Color(0xFFFFFF)
    }
}
