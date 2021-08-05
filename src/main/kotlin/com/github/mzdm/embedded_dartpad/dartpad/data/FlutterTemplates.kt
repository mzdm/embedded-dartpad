package com.github.mzdm.embedded_dartpad.dartpad.data

import com.github.mzdm.embedded_dartpad.dartpad.models.FlutterTemplate

fun getFlutterTemplate(flutterTemplate: FlutterTemplate, theme: String, widget: String?): String {
    return when (flutterTemplate) {
        is FlutterTemplate.None -> flutterNoneTemplate(widget)
        is FlutterTemplate.Stateful -> flutterStatefulTemplate(theme, widget)
        is FlutterTemplate.Stateless -> TODO("Not implemented yet.")
    }
}

private fun flutterNoneTemplate(widget: String?) = """
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

private fun flutterStatefulTemplate(theme: String, widget: String?): String {
    var methodWidget = widget
    if (widget != null && (widget.endsWith(",") || widget.endsWith(";"))) {
        methodWidget = widget.substring(0, widget.length - 1)
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
    return ${methodWidget ?: "Text('Hello, World!'),"};
  }
}

"""
}
