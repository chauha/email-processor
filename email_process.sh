#!/bin/sh



##directory where jar file is located    
dir=target

##jar file name
jar_name=email-0.0.1-SNAPSHOT-jar-with-dependencies.jar

mvn clean compile assembly:single 

## Launching application 
java  -jar $dir/$jar_name
