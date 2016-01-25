# README #

Alessandro Candolini
last updated: 01/25/2016

Java web scraping console app aimed at consuming a Sainsbury's fruit&vegetables product listing page (PLP) and returns a utf-8 json feed with the list of items and their details. The project uses the Maven Apache build manager.

The url of the PLP page is hardcoded at the beginning, nevertheless it's easy to improve the code in order to read the url from standard input.

## Dependencies ##

Since this is a Maven project, the list of dependencies is kept updated in the pov.xml file. 
For completeness, here is a list of the libraries currently used:

* [JUnit](http://junit.org/) (Java framework to write unit tests)
* [gson](https://github.com/google/gson)  (Java serialization library that can convert Java Objects into JSON and back)
* [Jsoup](http://jsoup.org/)  (Java HTML parser library)

## Build and Run Steps ##

First of all, clone this repository.
Fromcommand line you can browse to a new clean directory and prompt

```
#!bash

git clone https://acando86@bitbucket.org/acando86/sainsbury_test.git
```

Notice: the main application is com.sainsburytest.app.App;
### Eclipse ###

You need Eclipse with Maven support (M2Eclipse plugin). I have tested the behavior on 
* Eclipse Java EE IDEs.
* Version: Luna Release (4.4.0)
* m2e - Maven Integration for Eclipse (includes Incubating components)	1.5.0.20140606-0033

Steps:
1. Launch Eclipse
1. Select a new clean workspace
1. Import existing maven project
1. At this point the project should be available on eclipse and you can run it as java application by selecting run from menu 

The JUnit tests can be run by selecting the corresponding java class, right click and select "run as Junit test" using the Eclipse JUnit Launcher 

### Maven command-line build ###

In order to build and run from the command line, you need Maven installed and configured in your system.
I have used the following configuration
* Apache Maven 3.3.9 

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

This core functionalities of this app are:
* load webpages
* scrape information from those webpages

Network requests are handle by the NetworkRequestExecutor class. The current implementation relies on the Jsoup met



## Who do I talk to? ##

For information, feedback, suggestions, feel free to contact me at alessandro.candolini@gmail.com