package com.wackywozniaks.email;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.mindrot.jbcrypt.BCrypt;

import com.wackywozniaks.beans.UserBean;

/**
 * This class handles the sending of emails using MailGun
 * 
 * Adapted from https://documentation.mailgun.com/quickstart-sending.html
 * 
 * @author WackyWozniaks Company
 */
public class SendEmail {

	/**
	 * The generic email sending method. Uses MailGun.
	 * 
	 * @param from The from email
	 * @param to The email to send to
	 * @param subject The email subject
	 * @param text The plaintext version of the message
	 * @param html The HTML version of the message
	 * @return The ClientResponse from the email
	 */
	public static ClientResponse sendEmail(String from, String to, String subject, String text, String html) {
		try {
			Client client = ClientBuilder.newClient();
			client.register(HttpAuthenticationFeature.basic(
					"api",
					System.getenv("EMAIL_KEY") //Our secret key
					));

			WebTarget mgRoot = client.target("https://api.mailgun.net/v3");

			Form reqData = new Form();
			reqData.param("from", from);
			reqData.param("to", to);
			reqData.param("subject", subject);
			reqData.param("text", text);
			reqData.param("html", html);

			return mgRoot.path("/{domain}/messages").resolveTemplate("domain", "mail.wackywozniaks.com")
					.request(MediaType.APPLICATION_FORM_URLENCODED).buildPost(Entity.entity(reqData, MediaType.APPLICATION_FORM_URLENCODED))
					.invoke(ClientResponse.class);
		}
		catch(Throwable e) {
			Logger.getLogger(SendEmail.class.getName()).log(Level.WARNING, e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * This method sends an email to the user for verification.
	 * 
	 * @param bean The user who needs verification
	 * @return The ClientResponse from the email
	 */
	public static ClientResponse sendVerificationEmail(UserBean bean) {
		String link = System.getProperty("URL") + "verify?hash=" + BCrypt.hashpw(bean.getEmail(), BCrypt.gensalt());
		
		return sendEmail("Wacky Wozniaks <no-reply@wackywozniaks.com>", bean.getEmail(), "Wacky Wozniaks Email Verification", 
				"Your email does not support HTML!", 
				"<html>Hi " + bean.getFirstName() + ",<br><br>Thank you for signing up for Wacky Wozniaks! Please verify your email using this link: " + link + "<br><br>Sincerely,<br><br>The Wacky Wozniaks Team</html>");
	}

}
