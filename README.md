# Spotify Desktop Client test automation framework
Author: eugene.bakulin@gmail.com
Date: 7/4/2017

## Overview
This framework supports design and execution of automated tests for Spotify desktop client. 

### Supported platforms
Framework is designed to support cross-platform tests. Current implementation is performed and tested on Windows machine, but methods for Mac are implemented as well (might need additional testing).

## Preconditions
Framework is required to perform as expected with next preconditions: 
* App is installed and configured to be run correctly (firewall, antivirus, etc.)
* App is resized to min width and height (to cover most complicated scenario)
* Machine used for tests is not simultaneously used by any other person of program (i.e. no interaction with mouse / keyboard)
* Connection to Spotify server is stable and can be established  in less than 10 seconds
* Advertisement pop ups do not appear during test execution
* Machines that will be used for testing have Spotify app installed in same location (properties file needs to be updated)

## Launching tests
Tests can be launched with maven comand through console:
'project_folder/mvn test'

## Scope
Tests cover next scenarios:
LoginTest
* Log in as valid user and verify that user is correctly logged in
* Try to log in as invalid user and verify that error message is displayed
* Log in as valid user with "remember me" unchecked, log out and verify that username was not saved
* Search for artist and verify that expected image is displayed
* SEarch for artist, launch song and verify that song is played indeed

## Framework Architecture
This framework is implemented using Page-Object Model pattern.

### Pages
Each screen is represented by a separate class, describing elements of screen and implementing methods that interact with it. Pages are stored in /src/main/java/com.bakulin.spotify package. All classes extend Page class.

### Tests
Each test limits its content to design, leaving all implementation for Page classes. Tests are stored in src/test/java/com.bakulin.spotify package. All tests extend SpotifyBasicTest class.

### Utils
Utils support execution of tests and stored in /src/main/java/com.bakulin.spotify.utils package. 

### Logging
For simplicity, logging is performed using System.output.printl / printf. Usage of 3rd party loggers is possible.

### Test data
There are multiple approaches to store test data. For visibility, .properties file was chosen for this framework. Default .properties file is stored in src/main/resources folder.
There are multiple ways to store test data: properties file, XML, JSON and all have pros and cons. Test data can also be passed through test arguments using console and replace default values.

### Images
Images should be stored in JAR for real production framework. For this task, images are stored in src/main/resources to maintain high visibility and transparency of used test data.

### Specific methods
Detailed documentation is available in code in JavaDoc format.

## Tools
* Java 1.7
* JUnit 4.12
* Maven
* Sikuli 1.1.1