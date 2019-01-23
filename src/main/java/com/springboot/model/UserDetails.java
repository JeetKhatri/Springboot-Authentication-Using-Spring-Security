package com.springboot.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/***
 * 
 * @author Jeet Khatri
 * @date 09-Jan-2019
 * @link https://github.com/JeetKhatri/Springboot-Authentication-Using-Spring-Security.git
 *
 */

@Entity
@Table(name = "UserDetails")
public class UserDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String number;
    
    @NotBlank
    private String password;

    @ManyToOne
    private Roles roles;
    
    private String resetpasswordtoken;
    
    private Date resetpasswordexpirationDate;
    
	public String getResetpasswordtoken() {
		return resetpasswordtoken;
	}

	public void setResetpasswordtoken(String resetpasswordtoken) {
		this.resetpasswordtoken = resetpasswordtoken;
	}

	public Date getResetpasswordexpirationDate() {
		return resetpasswordexpirationDate;
	}

	public void setResetpasswordexpirationDate(Date resetpasswordexpirationDate) {
		this.resetpasswordexpirationDate = resetpasswordexpirationDate;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setExpiryDate(int minutes){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.resetpasswordexpirationDate = now.getTime();
    }

    public boolean isExpired() {
        return new Date().after(this.resetpasswordexpirationDate);
    }
}
