package com.github.mzdm.embedded_dartpad.dartpad.models

data class PadSettings(
    val theme: Theme = Theme.DARK,
    val pad: Pad = Pad.FLUTTER,
    val flutterTemplate: FlutterTemplate = FlutterTemplate.None(null),
    val code: String? = null,
)
