package com.github.mzdm.embedded_dartpad.helpers

import com.github.mzdm.embedded_dartpad.models.FlutterTemplate
import com.github.mzdm.embedded_dartpad.models.Theme

fun getFlutterTemplate(flutterTemplate: FlutterTemplate, theme: Theme, widget: String?): String {
    val appTheme = theme.toString()
        .toLowerCase()
    return when (flutterTemplate) {
        is FlutterTemplate.None -> flutterNoneTemplate(widget)
        is FlutterTemplate.Stateful -> flutterStatefulTemplate(appTheme, widget)
        is FlutterTemplate.Stateless -> TODO("Implement more templates")
    }
}

fun dartTemplate(code: String?) = """
void main() {
  ${code ?: "print('Hello, World!');"}
}
"""

fun flutterNoneTemplate(widget: String?) = """
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State {
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      // TODO: Change to your widget
      home: FlutterLogo(),
    );
  }
}

${widget ?: ""}

"""

fun flutterStatefulTemplate(theme: String, widget: String?): String {
    var editedWidget = widget
    if (widget != null && (widget.endsWith(",") || widget.endsWith(";"))) {
        editedWidget = widget.substring(0, widget.length - 1)
    }
    return """
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State {
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        brightness: Brightness.$theme,
      ),
      home: Scaffold(
        body: Center(
          child: MyStatefulWidget(),
        ),
      ),
    );
  }
}

class MyStatefulWidget extends StatefulWidget {
  @override
  _MyStatefulWidgetState createState() => _MyStatefulWidgetState();
}

class _MyStatefulWidgetState extends State<MyStatefulWidget> {
  @override
  Widget build(BuildContext context) {
    return ${editedWidget ?: "Text('Hello, World!'),"};
  }
}

"""
}
