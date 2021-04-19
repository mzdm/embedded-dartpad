package com.github.mzdm.embedded_dartpad.utils

import com.github.mzdm.embedded_dartpad.helpers.dartTemplate
import com.github.mzdm.embedded_dartpad.helpers.getFlutterTemplate
import com.github.mzdm.embedded_dartpad.models.DartPadSettings
import com.github.mzdm.embedded_dartpad.models.Pad
import com.github.mzdm.embedded_dartpad.models.Theme
import org.apache.commons.lang.StringEscapeUtils

class HtmlContentRenderer {
    companion object {
        fun load(dartPadSettings: DartPadSettings): String {
            val code = dartPadSettings.code

            // TODO: Change app theme as well(?)
//            val theme = dartPadSettings.theme
            val appTheme = Theme.LIGHT
            val frameTheme = "dark"

            val pad = dartPadSettings.pad.name.toLowerCase()
            val flutterTemplate = dartPadSettings.flutterTemplate

            val codeTemplate = StringEscapeUtils.escapeHtml(
                when (dartPadSettings.pad) {
                    Pad.DART -> dartTemplate(code)
                    Pad.FLUTTER -> getFlutterTemplate(flutterTemplate, appTheme, code)
                },
            )

            return """
                <!DOCTYPE html>
    
    <!-- Copyright (c) 2019, the Dart project authors.  Please see the AUTHORS file
         for details. All rights reserved. Use of this source code is governed by a
         BSD-style license that can be found in the LICENSE file. -->
    
    <html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    
        <title>IntelliJ Embedded DartPad</title>
    
        <script type="text/javascript" src="https://dartpad.dev/inject_embed.dart.js" defer></script>
        <style>
            .dartpad-embed > * {
                width: 100%;
                height: 100%;
            }
    
            html, body, iframe {
                height: 100%;
                width: 100%;
                margin: 0;
                padding: 0;
            }
    
            div {
                height: 100%;
            }
    
            iframe {
                border: 0 solid;
            }
        </style>
    </head>
    
    <body>
    <pre>
                <code class="language-run-dartpad:theme-${frameTheme}:mode-$pad:run-true:split-50:null_safety-true">
    $codeTemplate
                </code>
            </pre>
    </body>
    </html>
            """
        }
    }
}
