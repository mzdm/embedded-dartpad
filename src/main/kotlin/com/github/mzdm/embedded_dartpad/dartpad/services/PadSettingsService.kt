package com.github.mzdm.embedded_dartpad.dartpad.services

import com.github.mzdm.embedded_dartpad.dartpad.models.PadSettings
import com.github.mzdm.embedded_dartpad.dartpad.models.FlutterTemplate
import com.github.mzdm.embedded_dartpad.dartpad.models.Pad

// Project service
class PadSettingsService {
    var settings: PadSettings = PadSettings()
        get() = field
        private set

    fun setPad(newPad: Pad) {
        settings = settings.copy(pad = newPad)
    }

    fun setWidget(newWidget: String?) {
        settings = settings.copy(code = newWidget)
    }

    fun setTemplate(newPadTemplate: FlutterTemplate) {
        settings = settings.copy(flutterTemplate = newPadTemplate)
    }

    fun reset() {
        settings = PadSettings()
    }
}
