# Embedded DartPad

![Build](https://github.com/mzdm/embedded-dartpad/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/com.github.mzdm.embedded_dartpad.svg)](https://plugins.jetbrains.com/plugin/com.github.mzdm.embedded_dartpad)

[comment]: <> ([![Downloads]&#40;https://img.shields.io/jetbrains/plugin/d/com.github.mzdm.embedded_dartpad.svg&#41;]&#40;https://plugins.jetbrains.com/plugin/com.github.mzdm.embedded_dartpad&#41;)

## Template ToDo list

- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Verify the [pluginGroup](/gradle.properties), [plugin ID](/src/main/resources/META-INF/plugin.xml)
  and [sources package](/src/main/kotlin).
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate)
  for the first time.
- [ ] Set the Plugin ID in the above README badges.
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified
  about releases containing new features and fixes.

<!-- Plugin description -->
<p>This plugin integrates DartPad directly into IntelliJ environment which lets you run Flutter &amp; 
Dart code samples with ease.</p>

<p align="center">
<img height="auto" width="700" src="previews/preview.gif" />
</p>

<h3 id="suggestions-bugs">Suggestions &amp; bugs</h3>
<p>Any suggestions and/or bugs please report to the <a href="https://github.com/mzdm/embedded-dartpad">related GitHub repository</a>.</p>

<h3 id="troubleshooting">Troubleshooting</h3>
<p>Refer to <a href="https://plugins.jetbrains.com/docs/intellij/jcef.html#enabling-jcef">IntelliJ documentation</a> page.
<br><br>
<em>Dart and Flutter and the related logos are trademarks of Google LLC. This is not endorsed by or affiliated with Google LLC.</em></p>
<!-- Plugin description end -->

## Installation

- Using IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "
  embedded-dartpad"</kbd> >
  <kbd>Install Plugin</kbd>

- Manually:

  Download the [latest release](https://github.com/mzdm/embedded-dartpad/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
