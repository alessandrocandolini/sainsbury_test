# README #

Alessandro Candolini
last updated: 01/25/2016

Maven project that implements a Java web scraping console app aimed at consuming Sainsbury's fruit&vegetables test page and returns a utf-8 json feed with the list of items. 


The url of the page is hardcoded, but it's easy to slightly change the code to accept an url as input.


## Build and Run Steps ##

First of all, clone this repository.
Fromcommand line you can browse to a new clean directory and prompt

```
#!bash

git clone https://acando86@bitbucket.org/acando86/sainsbury_test.git
```

Notice: the main application is com.sainsburytest.app.App;
### Eclipse ###

1. Launch Eclipse
1. Select a new clean workspace
1. Import existing maven project
1. At this point the project should be available on eclipse and you can run it as java application by selecting run from menu 

The JUnit tests can be run by selecting the corresponding java class, right click and select "run as Junit test" using the Eclipse JUnit Launcher 

### Maven command-line build ###

In order to build and run from the command line, you need Maven installed and configured in your system.

Navigate to the folder where you have cloned this repository and type, 
```
#!bash
cd my-app
mvn package
```

This should build a jar with dependencies called my-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar  in the target folder. 
You can run it by simply prompting 
```
#!bash
cd target
java -jar my-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar

```

This way, all unit tests should be processed during the build process. 


### Warning ###

Just for backup purposes, a copy of my-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar has been committed as well to this repository. Do not use it. 

## Short description ##


### Who do I talk to? ###

For information, feedback, suggestions, feel free to contact me at alessandro.candolini@gmail.com