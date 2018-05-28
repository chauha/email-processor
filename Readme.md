### What : Utillity to parse latest email for a given email account and reply with found `ip's` `domain names` and `urls`.

By design it only reads the latest email in your inbox and searches for relevant info in that.

##### How to run or build.
Buid : `mvn clean compile assembly:single`  
Launch this app : `bash email_process.sh` or alternatively `java -jar email-0.0.1-SNAPSHOT-jar-with-dependencies.jar` from root dir.
 It will ask gmail account and password of account you want to monitor.


### Notes:

1.Enable your gmail account settings with allow access to less secure apps in order to run this utillity.
2. It only parses `plain-text` emails only , so while sending emails to that account make sure you choose `plain-text` mode.
2.Utillity requires internet connectivity and works best while outside of any corporate firewall.
