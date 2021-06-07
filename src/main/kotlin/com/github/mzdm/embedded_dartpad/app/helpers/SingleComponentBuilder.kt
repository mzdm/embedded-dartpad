package com.github.mzdm.embedded_dartpad.app.helpers

import com.google.common.collect.ImmutableList
import javax.swing.JComponent
import javax.swing.JPanel

fun JPanel.addAll(components: ImmutableList<JComponent>) {
    for (component in components) {
        this.add(component)
    }
}
