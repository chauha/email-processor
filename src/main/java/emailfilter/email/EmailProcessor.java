package emailfilter.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;

public class EmailProcessor {

	private String host;
	private String storeType;
	private String userName;
	private String password;

	public EmailProcessor(String host, String storeType, String userName, String password) {
		this.host = host;
		this.storeType = storeType;
		this.userName = userName;
		this.password = password;

	}

	protected String readEmails() throws MessagingException, IOException {
		Properties properties = new Properties();
		properties.put("mail.pop3.host", host);
		properties.put("mail.pop3.port", 995);
		String body = "";

		Session emailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		Store store = emailSession.getStore(storeType);
		store.connect(host, 993, userName, password);
		Folder emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);

		Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.RECENT), false));
		System.out.println("email-count" + emailFolder.getMessageCount());
		System.out.println("messages.length---" + messages.length);

		for (int index = messages.length - 1, n = messages.length - 1; index >= n; index--) {
			Message message = messages[index];
		
			System.out.println("Processing email with Subject: " + message.getSubject());
			System.out.println("Sent From: " + message.getFrom()[0]);
			
			body = message.getContent().toString();
		}
		return body;
	}

	protected void sendResponse(String _body) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session emailSession = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		try {

			Message message = new MimeMessage(emailSession);
			message.setFrom(new InternetAddress("relevant-info@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userName));
			message.setSubject("Relevant data");
			if (_body.isEmpty()) {
				message.setText("Nothing relevant found :( !!");
			} else {
				message.setText("Here's what i found, \n" + _body);
			}
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
