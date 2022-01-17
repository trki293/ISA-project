package com.isa.booking_entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.reports.StatusOfReport;

@Service
public class EmailService {
	private JavaMailSender javaMailSender;
    private Environment env;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, Environment env) {
        this.javaMailSender = javaMailSender;
        this.env = env;
    }
    
    @Async
    public void sendConfirmationMail(String email, String confirmationToken) throws MailException, InterruptedException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Confirm registration");
        mail.setText("Please confirm your registration by clicking the link below \n\n"+ "http://localhost:8080/auth/confirm_account/" + confirmationToken);
        javaMailSender.send(mail);
    }
    
    @Async
    public void sendReservationConfirmationEmail(String email, String typeOfReservation) throws MailException, InterruptedException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Confirmation of booking");
        mail.setText("You have successfully made a reservation of "+typeOfReservation+"!\n\nBest Regards.");
        javaMailSender.send(mail);
    }
    
    @Async
    public void sendResponseToComplaint(String email, String textOfComplaintResponse, String typeOfComplaint) throws MailException, InterruptedException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Response to the "+typeOfComplaint+" complaint");
        mail.setText(textOfComplaintResponse);
        javaMailSender.send(mail);
    }
    
    @Async
	public void sendMailForDeletingUser(String email, String emailText) throws MailException, InterruptedException {
		SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Deleting account");
        mail.setText(emailText);
        javaMailSender.send(mail);
	}
    @Async
	public void sendMailAboutPublishReview(String email) throws MailException, InterruptedException{
		SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Review published");
        mail.setText("Review published. Best Regards!");
        javaMailSender.send(mail);
    }
    
    @Async
   	public void sendNotificationAboutPenaltyPoints(String email, String clientEmail, StatusOfReport statusOfReport) throws MailException, InterruptedException{
       	SimpleMailMessage mail = new SimpleMailMessage();
           mail.setTo(email);
           mail.setFrom(env.getProperty("spring.mail.username"));
           mail.setSubject("Penalty points");
           mail.setText("The client whose email "+clientEmail+" "+(statusOfReport==StatusOfReport.APPROVED ? "recived" : "did not recive")+" the penalty point!");
           javaMailSender.send(mail);
   	}
    
    @Async
   	public void sendNotificationAboutApprovedRegistrationRequest(String email, String emailText) throws MailException, InterruptedException{
       	SimpleMailMessage mail = new SimpleMailMessage();
           mail.setTo(email);
           mail.setFrom(env.getProperty("spring.mail.username"));
           mail.setSubject("Registration request");
           mail.setText(emailText);
           javaMailSender.send(mail);
   	}
}
