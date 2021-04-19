package com.github.mzdm.embedded_dartpad.models

data class DartPadSettings(
    // TODO: Implement themes
    val theme: Theme = Theme.DARK,

    val pad: Pad = Pad.FLUTTER,
    val flutterTemplate: FlutterTemplate = FlutterTemplate.None(null),
    val sample: Sample = Sample.NO_SAMPLE,
    val code: String? = null,
) {

    // TODO: Implement custom GitHub gist IDs
    fun getSampleId(): String {
        return if (sample == Sample.NO_SAMPLE) {
            when (pad) {
                Pad.DART -> Sample.HELLO_WORLD.id
                Pad.FLUTTER -> Sample.COUNTER.id
            }
        } else ""
    }
}
