package com.github.mzdm.embedded_dartpad.dartpad.data

fun getDartTemplate(code: String?): String {
    return dartMainTemplate(code)
}

private fun dartMainTemplate(code: String?) = """
void main() {
  ${code ?: "print('Hello, World!');"}
}
"""
