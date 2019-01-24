package com.springboot.service;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.Roles;
import com.springboot.model.UserDetails;
import com.springboot.repository.RolesRepository;
import com.springboot.repository.UserDetailsRepository;

/***
 * 
 * @author Jeet Khatri
 * @date 09-Jan-2019
 * @link https://github.com/JeetKhatri/Springboot-Authentication-Using-Spring-Security.git
 *
 */

@Service
public class UserDetailsServiceImpl {

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<UserDetails> getListOfAdminDetails() {
		return userDetailsRepository.findByRolesName("admin");
	}
	public List<UserDetails> getListOfUserDetails() {
		return userDetailsRepository.findByRolesName("user");
	}

	public String addUserDetails(@Valid UserDetails userDetails) {
		Roles roles = rolesRepository.findByName("user");
		if(roles != null) {
			userDetails.setRoles(roles);
		}
		userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		return userDetailsRepository.save(userDetails).getName();
	}

	public UserDetails updateUserDetails(@Valid UserDetails userDetails, int id) {
		UserDetails userDetails1 = userDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetails", "id", id));

		userDetails1.setName(userDetails.getName());
		userDetails1.setNumber(userDetails.getNumber());
		userDetails1.setPassword(userDetails.getPassword());

        return userDetailsRepository.save(userDetails1);
	}

	public UserDetails getUserDetails(int id) {
		// lambda expression
		return userDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserDetails", "id", id));
	}

	public ResponseEntity<?> deleteUserDetails(int id) {
		// lambda expression
		UserDetails userDetails = userDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserDetails", "id", id));
		userDetailsRepository.deleteById(userDetails.getId());
		return ResponseEntity.ok().build();
	}
	
	public String forgotPassword(String name) {
		UserDetails userDetails = userDetailsRepository.findByName(name);
		if(userDetails == null) {
			return "Account not exist.";
		}else {
			String token = UUID.randomUUID().toString();
			userDetails.setResetPasswordToken(token);
			userDetails.setExpiryDate(60*24);
			userDetailsRepository.save(userDetails);
			
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			   
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			      protected PasswordAuthentication getPasswordAuthentication() {
			         return new PasswordAuthentication("jeet.khatri1@gmail.com", "*****");
			      }
			});
			Message msg = new MimeMessage(session);
			try {
			   msg.setFrom(new InternetAddress("jeet.khatri1@gmail.com", false));
			   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("jeet.khatri1@gmail.com"));
			   msg.setSubject("Tutorials point email");
			   msg.setSentDate(new Date());

			   MimeBodyPart messageBodyPart = new MimeBodyPart();
			   messageBodyPart.setContent("Tutorials point email1<br/><br/><br/><a href=\"http://localhost:6018/users/validate-password-token?resetpasswordtoken="+token+"\">Reset Password</a><br/>", "text/html");

			   Multipart multipart = new MimeMultipart();
			   multipart.addBodyPart(messageBodyPart);
			  // MimeBodyPart attachPart = new MimeBodyPart();
			   //attachPart.attachFile("/var/tmp/image19.png");
			   //multipart.addBodyPart(attachPart);
			   msg.setContent(multipart);
			   Transport.send(msg); 
			
			} catch (Exception e) {
					e.printStackTrace();
			}
			return "password sent to your email";
		}
	}

	public boolean validatePasswordToken(String resetPasswordToken, Model model) {
		UserDetails userDetails = userDetailsRepository.findByResetPasswordToken(resetPasswordToken);
		if(userDetails != null && !userDetails.isExpired()) {
			model.addAttribute("resetPasswordToken",resetPasswordToken);
			return true;
		}
		return false;
	}

	public String resetPassword(String password, String confirmPassword, String resetPasswordToken) {
		if(password.equals(confirmPassword)) {
			UserDetails userDetails = userDetailsRepository.findByResetPasswordToken(resetPasswordToken);
			if(userDetails != null && !userDetails.isExpired()) {
				userDetails.setPassword(bCryptPasswordEncoder.encode(password));
				userDetails.setResetPasswordToken(null);
				userDetails.setResetPasswordExpirationDate(null);
				userDetailsRepository.save(userDetails);
				return "password updated.";
			}else {
				return "Invalid token";
			}
		}else {
			return "password and confirm password are not same";
		}
	}

	public String updatePassword(String existingPassword, String newPassword, String confirmPassword, Model model) {
		if(newPassword.equals(confirmPassword)) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth!=null) { 
				UserDetails userDetails = userDetailsRepository.findByName(auth.getName());
				if(userDetails != null) {
					if(BCrypt.checkpw(existingPassword, userDetails.getPassword())) {
						userDetails.setPassword(bCryptPasswordEncoder.encode(newPassword));
						userDetailsRepository.save(userDetails);
						model.addAttribute("message", "Password updated. ");
						return "welcome";
					}else {
						model.addAttribute("message","Entered existing password are not same as system password");
						return "updatePassword";
					}
				} else {
					model.addAttribute("message","User name "+auth.getName()+" not found");
					return "login";
				}
			}else{
				model.addAttribute("message","you are logged out. please login again.");
				return "login";
			}
		}else {
			model.addAttribute("message", "password and confirm password are not same ");
			return "updatePassword";
		}
	}
}