/**
 * 
 */
package com.publication.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.publication.constants.LoginStatus;
import com.publication.model.Login;

/**
 * @author Deepanshu Jain
 *
 */
public interface LoginDAO {

	public LoginStatus validateLogin(String username , String password, String role);
	public Login getLogin(String username);
	public List<Login> getAllLogins();
	public boolean saveNewLogin(Login login);
	public String getRoleBySessionID(String sessionID);
	public boolean insertSessionID(String id, String sessionID);
	public String getUsernameBySessionID(String sessionID);
	public void deleteSessionID(String sessionID);
	public String forgotRequest(String username) throws IOException;
	public boolean reForgotRequest(String token, String username, Timestamp timestamp) throws IOException;
	public String getEmailByUsername(String username);
	public Object[] forgotResponse(String token);
	public boolean finishForgetPassword(String token, String password);
}
