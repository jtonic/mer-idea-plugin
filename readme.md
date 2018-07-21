
Example project to getting acquainted with IntelliJ Plugin Development.

Imp:
- For a offline repository (where when downloading from the internet is not an option):
    - get the lib/ideaIC-173.3727.127.zip archive and 
    - unzip it under the lib folder

TODO:
- how to add a new plugin
- [>] how to add a new maven remote repository
    
- create a new wizard to
    - create a project using an archetype
    - some of the archetype input properties has to be filled in UI
- create a Mer framework
- autodetect Mer framework by:
    - finding out the junit in the pom.xml dependencies
- Mer settings
- sticky balloon should have a [link](http://blog.thibaulthelsmoortel.be/java/clickable-links-balloon-notifications-intellij-plugin/) to open:
    - a web page opened with the default system browser
    - a dialog...
    - or a tool window (activate)
- kotlin and java in the same source folder (src/main/java)

- Issues:
    - the newly added plugin is not displayed in settings maven remote repositories.
