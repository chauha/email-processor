### What : Utillity to parse latest email for a given email account and reply with found `ip's` `domain names` and `urls`.

By design it only reads the latest email in your inbox and searches for relevant info in that.

##### : How to run or build.
Buid : `mvn clean compile assembly:single`  
Launch this app : `bash email_process.sh` . It will ask gmail account and password of account you want to monitor.


###Notes:

1.Enable your gmail account settings with allow access to less secure apps in order to run this utillity.
2.Utillity requires internet connectivity and works best while outside of any corporate firewall.
