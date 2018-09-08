package com.automation.bsr.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

	Multipart multipart;

	public void sendMail() throws Exception {
		// Setting up configurations for the email connection to the Google SMTP
		// server using TLS
		Properties props = new Properties();
		props.put("mail.smtp.host", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		// Establishing a session with required user details
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("BsrUsaiPad@gmail.com", "bsr123@#");
			}
		});
		try {
			// Creating a Message object to set the email content
			MimeMessage msg = new MimeMessage(session);
			// Storing the comma seperated values to email addresses
			String to = "Vaibhav.Choudhary@perficient.com";
			/*
			 * Parsing the String with default delimiter as a comma by marking
			 * the boolean as true and storing the email addresses in an array
			 * of InternetAddress objects
			 */
			InternetAddress[] address = InternetAddress.parse(to, true);
			// Setting the recepients from the address variable
			msg.setRecipients(Message.RecipientType.TO, address);
			String timeStamp = new SimpleDateFormat("yyyyMMdd_hh-mm-ss").format(new Date());
			msg.setSubject("Sample Mail : " + timeStamp);
			msg.setSentDate(new Date());
			msg.setText("Sampel System Generated mail");
			msg.setHeader("XPriority", "1");

			/*
			 * // Create another object to add another content MimeBodyPart
			 * messageBodyPart2 = new MimeBodyPart();
			 * 
			 * // Mention the file which you want to send String filename =
			 * "target/cucumber-reports-html/cucumber-html-reports/overview-features.html";
			 * 
			 * // Create data source and pass the filename DataSource source =
			 * new FileDataSource(filename);
			 * 
			 * // set the handler messageBodyPart2.setDataHandler(new
			 * DataHandler(source));
			 * 
			 * // set the file messageBodyPart2.setFileName(filename);
			 */
			// Create object of MimeMultipart class
			multipart = new MimeMultipart();
			//addAttachment(multipart, "target/cucumber-reports-html/cucumber-html-reports/overview-failures.html");
			//listFiles("target/cucumber-reports-html/cucumber-html-reports");
			//listFiles("target/cucumber-reports-html/cucumber-html-reports/css");
			//listFiles("target/cucumber-reports-html/cucumber-html-reports/embeddings");
			//listFiles("target/cucumber-reports-html/cucumber-html-reports/fonts");
			//listFiles("target/cucumber-reports-html/cucumber-html-reports/images");
			listFiles("target/cucumber-reports-html/cucumber-html-reports/js");
			// addAttachment(multipart,
			// "target/cucumber-reports-html/cucumber-html-reports/overview-features.html")
			// add body part 1
			// multipart.addBodyPart(messageBodyPart2);

			// set the content
			msg.setContent(multipart);
			Transport.send(msg);
			System.out.println("Mail has been sent successfully");
		} catch (MessagingException mex) {
			System.out.println("Unable to send an email" + mex);
		}
	}

	private static void addAttachment(Multipart multipart, String filename) throws MessagingException {
		DataSource source = new FileDataSource(filename);
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);
	}
	
	public void listFiles(String filePath) throws MessagingException {
		File f = new File(filePath);
		File[] attachments = f.listFiles();
		for (File file : attachments) {
			if (!file.isDirectory())
			{
				System.out.println("-----------"+file);
				addAttachment(multipart, file.toString());
			}

		}
	}

	public static void main(String[] args) {

		try {
			new Email().sendMail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
