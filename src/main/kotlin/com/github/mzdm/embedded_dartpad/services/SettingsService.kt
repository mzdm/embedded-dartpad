package com.github.mzdm.embedded_dartpad.services

import com.github.mzdm.embedded_dartpad.models.DartPadSettings
import com.github.mzdm.embedded_dartpad.models.FlutterTemplate
import com.github.mzdm.embedded_dartpad.models.Pad

// Project service
class SettingsService {
    var settings: DartPadSettings = DartPadSettings()
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
        settings = DartPadSettings()
    }
}
