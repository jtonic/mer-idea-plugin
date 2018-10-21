
Sample project to get acquainted with Intellij Plugin Development.
---

- The name of the plugin: Merak (the green peacock from Malaysia)
- The plugin almost completely written in kotlin (except forms which are in java)
- [Todo list](todo.md)
- [Plugin development tips](plugin-development-tips.md)
- Testing the plugin locally
```bash
./gradlew runIde
```
- build the plugin zip archive to install it from local file system
```bash
./gradlew buildPlugin
```

- Offline work - when downloading from the internet is forbidden:
    - get the lib/ideaIC-173.3727.127.zip archive and 
    - unzip it under the lib folder
    - _**WIP**_ 
        - see how to configure runWithDifferentJvm
        - where to put in .gradle user home dir the Jebrains JVM to use it when runIde
    

