[![Build Status](https://travis-ci.org/VMormoris/EncDec.svg?branch=master)](https://travis-ci.org/VMormoris/EncDec) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

EncDec is a library that helps you create binary data from Java variables and the other way around.

# Documentation
Extended documentation on how to use the library can be found [here](http://mormoris.gr/EncDec/doc).

# Building
It is highly recommended to build the library yourself, but if you are not up for it you can find JARs for some versions of OpenJDK [here](http://mormoris.gr/EncDec/bin).

* __With Command line:__<br>
  `mkdir build`<br>
  `javac -d build ./src/encdec/*.java`<br>
  `cd ./build`<br>
  `jar -cvf EncDec.jar ./encdec`<br>

* __With Eclipse IDE:__<br>
  _Right click the encdec package._<br>
  _Select Export._<br>
  _Under Java select JAR file and click Next._<br>
  _In the "Select the export destination" section Click Browse and choose where you want the .jar file to be exported._<br>
  _Click Finish._<br>

* __With IntelliJ IDEA:__<br>
  _Click File and then Project Structure._<br>
  _Under "Project Settings" section select Artifact._<br>
  _Click the plus sign, select JAR and "From modules with dependencies"._<br>
  _Click OK._<br>
  _Click Build and Build Artifacts._

# Using
* __With Command line:__<br>
  _Navigate to your project directory._<br>
  _Create a directory named lib._<br>
  _Put the EncDec.jar in the lib directory you just created._<br>
  _Compile:_ `javac -cp ./lib/EncDec.jar Test.java`<br>
  _Run:_ `java -cp .:lib/EncDec.jar Test`<br>

* __With Eclipse IDE:__<br>
  _Right Click on your project and select New Folder._<br>
  _Write lib on the "Folder name" section and Click Finish._<br>
  _Put the EncDec.jar in the lib directory you just created._<br>
  _Right Click on your project and select Configure Build Path under Build Path section._<br>
  _Select Java Build Path and Libraries._<br>
  _Click Add JARs and select the EncDec.jar located in the lib directory._<br>
  _Click Apply and Close._<br>

* __With IntelliJ IDEA:__<br>
  _Right Click on your Project, select New and Directory._<br>
  _Name the directory lib and Click OK._<br>
  _Put the EncDec.jar in the lib directory you just created._<br>
  _Click File and then Project Structure._<br>
  _Under "Project Settings" section select Modules._<br>
  _In the Dependencies tab Click the plus sign and select JARs or directories._<br>
  _Select the EncDec.jar Click OK and then Apply._<br>

* __With Android Studio:__<br>
  _Switch the folder structure from Android to Project._<br>
  _Copy the EncDec.jar in libs folder inside app._<br>
  _Right Click the EncDec.jar and Click "Add as library"._

# Contributing

* _Fell free to contribute to any of the [open issues](https://github.com/VMormoris/EncDec/issues) or open a new one describing what you want to do._
* _For small-scale improvements and fixes simply submit a GitHub pull request._

# Community

Please [join](https://discord.gg/Fz2rteF) our Discord server to ask questions about the library and help others with their questions.
