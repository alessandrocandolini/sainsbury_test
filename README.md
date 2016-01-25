# README #

Alessandro Candolini
last updated: 01/25/2016

Maven project that implements a Java web scraping console app aimed at consuming Sainsbury's fruit&vegetables test page and returns a utf-8 json feed with the list of items. 

## Build and Run Steps ##

First of all, clone this repository on your computer.
For example, from bash command line you can browse to a new clean directory and prompt

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
Once done, you can navigate to the folder where you have cloned this repository and type 
```
#!bash
mvn clean compile assembly:singlet
```

This should build a jar with dependencies called my-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar and you can run it by simply prompting [from the directory where the file is created]
```
#!bash
java -jar my-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar

```
### Stand-alone runnable jar ###

Just for backup purposes, a copy of my-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar has been committed as well to this repository

## Description ##



### Who do I talk to? ###

For information, feedback, suggestions, feel free to contact me at alessandro.candolini@gmail.com