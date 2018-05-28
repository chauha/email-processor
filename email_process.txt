#!/bin/sh



##directory where jar file is located    
dir=target

##jar file name
jar_name=email-0.0.1-SNAPSHOT-jar-with-dependencies.jar

#commenting / enable if you have mvn in your path.
##mvn clean compile assembly:single 

## Launching application 
java  -jar $jar_name
