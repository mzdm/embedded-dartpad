package com.github.mzdm.embedded_dartpad.components

import com.intellij.ui.layout.InnerCell
import java.awt.Color

fun InnerCell.expandedX() {
    button("") { }.constraints(pushX, growX).component.apply {
        isOpaque = false
        isBorderPainted = false
        foreground = Color(0xFFFFFF)
    }
}
