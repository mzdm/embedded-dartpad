package com.github.mzdm.embedded_dartpad.dartpad.models

sealed class FlutterTemplate {
    data class None(val widget: String?) : FlutterTemplate()
    data class Stateful(val theme: Theme, val widget: String?) : FlutterTemplate()
    data class Stateless(val theme: Theme, val widget: String?) : FlutterTemplate()
}
