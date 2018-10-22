

Terminology:
---
1. UAST    Unified Abstract Syntax Tree (for java/kotlin)
1. PSI     Program Structure Inteface [link](https://www.jetbrains.org/intellij/sdk/docs/basics/architectural_overview/psi.html)     

APIs
---
1. LineMarkerProvider [link](https://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/line_marker_provider.html#define-a-line-marker-provider)

    ![line marker](/docs/pictures/line_marker_1.png)

Useful plugins
---
1. PSI Viewer - to see the PSI structure of the document open in the IDE editor

HowTo
---

1. Get information about a published plugin

    [link](https://www.jetbrains.org/intellij/sdk/docs/plugin_repository/api/plugin_details.html)
    
    [example](https://plugins.jetbrains.com/plugins/list?pluginId=org.intellij.scala)
    
    
1. How to get facets for a module

```kotlin
FacetManager.getInstance(module).getAllFacets(module) // returns Facet[]
```
