package emailfilter.email;

import java.io.IOException;
import java.util.Scanner;

import javax.mail.MessagingException;

import com.parse.info.EmailBodyParser;
import com.parse.info.ParseInformation;

public class EmailProcessorMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (;;) {
			System.out.println("Enter command 'process_account' to read email or `exit` to end");
			String line = sc.nextLine().toString();
			if (line.equalsIgnoreCase("exit")) {
				break;
			} else if (line.equalsIgnoreCase("")) {
				// Skip
			} else if (line.equals("process_account")) {
				System.out.println("Enter Email Address you want to monitor. Enter gmail account only");
				String email = sc.nextLine().toString();
				System.out.println("Enter password for this email account");
				String password = sc.nextLine().toString();
				EmailProcessor emailProcess = new EmailProcessor(EmailProcessorConstants.HOST,
						EmailProcessorConstants.STORETYPE, email, password);
				try {
					String bodyText=emailProcess.readEmails();
					ParseInformation emailParser = new EmailBodyParser();
					String results=emailParser.findRelevantEntries(bodyText);
					emailProcess.sendResponse(results);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					sc.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Something wrong with firewall or internet connection.");
					sc.close();
					e.printStackTrace();
				}
			}
		}
		sc.close();
	}

}
