# README #

Alessandro Candolini
last updated: 01/25/2016

Java web scraping console app aimed at consuming a Sainsbury's fruit&vegetables product listing page (PLP) and corresponding product pages (PDPs) and returns a utf-8 json feed with the list of items and their details. The project uses the Maven Apache manager as build tool.

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

The main application is com.sainsburytest.app.App

We describe two ways to build and run the project:

* using eclipse IDE
* using maven command-line tools.

Similar steps should be available also for other IDEs (IntelliJ IDEA, Netbeans, etc)

### Eclipse IDE ###

Before proceeding, you need a working [Eclipse IDE](https://eclipse.org/) installation with [Maven integration plugin M2Eclipse](http://www.eclipse.org/m2e/Maven). I have tested this guide using:

* Eclipse Java EE - Version: Luna Release (4.4.0)
* m2e - Maven Integration for Eclipse (includes Incubating components)	v1.5.0.20140606-0033

Steps:

1. Launch Eclipse IDE
1. Select a new clean workspace
1. From menu, select "Import" and choose "Import existing maven project"
1. At this point the project should be imported in the workspace and you should be able to run it as java application by selecting run from menu 

The JUnit tests can be run by selecting the corresponding java class, right click and select "run as Junit test" using the Eclipse JUnit Launcher 

### Maven command-line build ###

In order to build and run from the command line, you need [Maven](https://maven.apache.org/) installed and configured in your system.
I have used the following configuration:

* Apache Maven 3.3.9 

Navigate to the folder where you have cloned this repository and type, 
```
#!bash
cd my-app
mvn package
```

This should build a jar with dependencies called my-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar in the target folder (please check the location of the file from the output of the command). 
Once done, you can run the jar by simply prompting 
```
#!bash
cd target
java -jar my-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar

```

This way, all unit tests should be processed during the build process. 


### Warning ###

Just for backup purposes, a copy of my-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar has been committed as well to this repository. Do not use it! 

## Short description ##

The building blocks of this app are:

* load webpages
* scrape information from those webpages

Network requests are handle by the NetworkRequestExecutor class. 
The implementation is completely decoupled from any other part of the app, it is hidden within the method of this class and it can be changed at any time without impacting the other parts of the code.
This is possible since the method read a NetworkRequestPojo and returns a NetworkResponsePojo (without exposing objects such as Document, Response etc which are specific of the current implementation). 

Scraping is handled by the two classes PLPScraper and PDPScraper, which scrape the html body of a PLP or PDP respectively, and return the corresponding information. The scrapers accept as input a Webpage object.
In our application, the Webpage object is obtained via network request, but in principle we can also use a local HTML file (and actually we use this possibility for JUnit test: there are fake pages stored in the resources path), etc. For parsing, we rely on Jsoup library.



Finally, the classes inside operation package take care of gluing all this stuff together to product the result. A preliminary list of items is first created by retrieving and scraping the PLP; this list has all the items, but not all the details of the items are filled (because some information is missing on PLPs).
So, a loop is done over all items and for every item in the list its corresponding PDP is retrieved and parsed and the missing item details are filled.

We offer two different implementations of the loop over PDP [see corresponding methods inside ScrapingOperations]

* single-thread: the PDPs are retrieved and parsed sequentially
* multi-thread: the PDPs are retrieved and parsed sequentially using Future and ThreadPoolExecutor. 

The multi-thread implementation is basic, but it can provide a flavor of how the approach can be made more scalable. 

Finally, the total cost of the items is computed, all the information are stored in an instance of the ApplicationOutputDataPojo which is serialized and printed on the standard output.


## Legal disclamer ##

If this were not just a test, I should have put a legal disclaimer. Before doing Web scraping, always be aware of the Terms of Service of the website

## Who do I talk to? ##

For information, feedback, suggestions, feel free to contact me at alessandro.candolini@gmail.com