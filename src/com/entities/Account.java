package com.entities;
// Account class to store all the information gathered from SQL
public class Account {
	private int AccId;
	private String nric;
	private String name;
	private String DOB;
	private String password;
	private String loginId;
	private String email;
	private String securityQuestion;
	private String securityAnswer;
	private boolean valid = false;
	private int status ;
	private String role;
	private int NoOfAttempts;
	private int mobile;
	private int firstLogin;
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setAccId(int EmployeeId) {
		this.AccId = EmployeeId;
	}
	
	public int getAccId() {
		return AccId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	
	public String getNric() {
		return nric;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setBlocked(int status) {
		this.status = status;
	}
	
	public int isBlocked() {
		return status;
	}
	
	public void setDOB(String DOB){
		this.DOB = DOB;
	}
	
	public String getDOB(){
		return DOB;
	}
	
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	
	public int getMobile() {
		return mobile;
	}
	
	public void setAttempts(int attempts) {
		this.NoOfAttempts = attempts;
	}
	
	public int getAttempts() {
		return NoOfAttempts;
	}
	
	public void setFirstLogin(int firstLogin) {
		this.firstLogin = firstLogin;
	}
	
	public int getFirstLogin() {
		return firstLogin;
	}
}
