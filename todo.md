TODO list
---

- [>] Module implementation
- Exercises:
    - [V] _get all modules from project_
    - get dependencies of the modules
    - get module SDK
    - get the VirtualFile of the document opened in the editor
    - get File from the VirtualFile
    - get PSI element from VirtualFile
    - add a line marker for a VirtualFile element (field)
    - get file/virualfile/PSI from cache
    
    More info [here](https://www.jetbrains.org/intellij/sdk/docs/reference_guide/project_model/module.html),  [here](https://www.jetbrains.org/intellij/sdk/docs/basics/psi_cookbok.html), [here](https://www.youtube.com/watch?v=j2tvi4GbOr4)
    
- Add plugin development sdk sources
- create a new wizard to
    - for a single module maven project (spring-boot project)
    - create a project using an archetype
    - some of the archetype input properties has to be filled in UI
- add the Merak facet
- add the merak icon in the editor gutter - SpringBootApplication
- in the merak facet dialog (based on properties plugin - application properties file):
    - tracing - on/off
    - global logging - on/off
    - service discovery - on/off
- how to add a new maven remote repository
- How to discover a facet to a maven module (multi module maven project)?
- Merak facet/framework configuration based on application.properties files entries
    - my.settings.enabled=true (this are capabilities)
    
- Merak settings
- sticky balloon should have a [link](http://blog.thibaulthelsmoortel.be/java/clickable-links-balloon-notifications-intellij-plugin/) to open:
    - a web page opened with the default system browser
    - a dialog...
    - or a tool window (activate)
- kotlin and java in the same source folder (src/main/java)

Done
--
- autodetect Merak framework by:
    - find the pom.xml file and check if the parent's artifact has a specified value
- detect a Merak facet
- create a Merak framework

