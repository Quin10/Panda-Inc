package com.Modules.User;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
*
* @author Keaton Pennels u14373018
*/

public class User implements Serializable  {
	
	private Integer id;
    private String username;
    private String password;
    private Boolean isAdmin;
    private String firstname;
    private String email;
    private String lastname;
    private Boolean activated;
    private String activatedKey;
    private String resetKey;
   // @Temporal(javax.persistence.TemporalType.DATE)
    //@Temporal(javax.persistence.TemporalType.DATE)
    private Date resetDate;


	@SuppressWarnings("deprecation")
	public User()
	{
	            //Reset date 
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    java.util.Date date= new Date();
	    
	    
		int mnth = date.getMonth() + 5;
	    
		int year = date.getYear();
	    if(mnth > 11)
	    {
	        mnth = mnth - 11;
	        year++;
	    }
	    
	    date.setMonth(mnth);
	    date.setYear(year);
	    
	    this.resetDate = date;
	}
	
	public User(String uName,String password,Boolean isadmin,String lName,String fName, String email,Boolean activated,String activatedKey, String resetKey)
    {
        this.username = uName;
        this.password = password;
        this.isAdmin = isadmin;
        this.activated = activated;
        this.lastname = lName;
        this.firstname = fName;
        this.email = email;
        this.activatedKey = activatedKey;
        this.resetKey = resetKey;
        //Reset date 

    }
	 
    public User(String uName,String password)
    {
        this.username = uName;
        this.password = password;
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the isAdmin
     */
    public Boolean getIsAdmin() {
        if (isAdmin == null){
            return false;
        }

        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the activated
     */
    public Boolean getActivated() {
        if (activated == null){
            return false;
        }

        return activated;
    }

    /**
     * @param activated the activated to set
     */
    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    /**
     * @return the activatedKey
     */
    public String getActivatedKey() {
        return activatedKey;
    }

    /**
     * @param activatedKey the activatedKey to set
     */
    public void setActivatedKey(String activatedKey) {
        this.activatedKey = activatedKey;
    }

    /**
     * @return the resetKey
     */
    public String getResetKey() {
        return resetKey;
    }

    /**
     * @param resetKey the resetKey to set
     */
    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    /**
     * @return the resetDate
     */
    public Date getResetDate() {
        return resetDate;
    }

    /**
     * @param resetDate the resetDate to set
     */
    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate;
    }
}

