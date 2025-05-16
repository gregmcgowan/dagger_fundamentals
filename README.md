# Dagger Fundamentals

This is tutorial to illustrate the different features of the Dagger 2 Dependency Injection framework.

I created this in a previous job and thought it would be good to share it with everyone.

Although the project was intended for Android developers, the concepts are not Android specific, 
so I have changed it to a pure kotlin project. 

It will work with IntelliJ IDEA community edition.

Each package shows a different aspect of the SDK and will have a markdown file which will explain and summarise the key concepts.

I used [scabbard](https://arunkumar9t2.github.io/scabbard/) to generate the diagrams from the Dagger graph. Currently, it does not support KSP, so I have had to still use KAPT.

## Disclaimers:

* The illustration code won't actually do anything apart from occasional println(). You will be required to use your imagination somewhat. The focus  is on Dagger concepts rather than a fully functioning app.

* Some knowledge of Dependency Injection, JVM annotations and annotation processing is assumed 