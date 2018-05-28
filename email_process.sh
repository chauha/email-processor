#!/bin/sh



##directory where jar file is located    
dir=target

##jar file name
jar_name=email-0.0.1-SNAPSHOT.jar

mvn clean install 

## Launching application 
java  -cp "$LIB/*" -jar $dir/$jar_name
