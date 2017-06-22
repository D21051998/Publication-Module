package com.publication.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.Connection;
import java.util.List;

import com.publication.constants.BCrypt;
import com.publication.constants.LoginStatus;
import com.publication.dao.LoginDAO;
import com.publication.database.ConnectionFactory;
import com.publication.model.Login;

public class LoginIMPL  implements LoginDAO{

	@Override
	public LoginStatus validateLogin(String username , String password, String role) {
		
		
		Login login = getLogin(username);
		System.out.println(login);
		if(login == null){
			return LoginStatus.NO_SUCH_ACCOUNT_FOUND;
		}
		if(role.equals(login.getRole())){
			if(login.getStatus().equals("active")){
				System.out.println(BCrypt.hashpw(password, login.getSalt())+" "+login.getPassword());
				
				if(login.getPassword().equals(BCrypt.hashpw(password, login.getSalt()))){
					return LoginStatus.SUCCESS;
				}else{
					return LoginStatus.WRONG_PASSWORD;
				}
			}else{
				return LoginStatus.DEACTIVATED;
			}
		}else{
			return LoginStatus.NOT_AUTHORIZED;
		}
		
	}

	@Override
	public Login getLogin(String username) {
		Connection conn = null;
		PreparedStatement ps1 = null;
		try{
			conn = ConnectionFactory.getConnection();
			ps1 = conn.prepareStatement("select * from login where username=?");
			System.out.println(username);
			ps1.setString(1, username);
			ResultSet set = ps1.executeQuery();
			if(set.next()){
				Login login =  new Login();
				login.setUsername(set.getString("username"));
				login.setPassword(set.getString("password"));
				login.setStatus(set.getString("status"));
				login.setRole(set.getString("role"));
				login.setSalt(set.getString("salt"));
				return login;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(conn);
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Login> getAllLogins() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean saveNewLogin(Login login){
		Connection conn = null;
		PreparedStatement ps1 = null;
		System.out.println(login);
		try{
			conn = ConnectionFactory.getConnection();
			ps1 = conn.prepareStatement("insert into login (username, password, salt, role, status)  values (?,?,?,?,?)");
		String sall = BCrypt.gensalt();
			ps1.setString(1, login.getUsername());
			ps1.setString(2, BCrypt.hashpw(login.getPassword(), sall));
			ps1.setString(3,  sall);
			ps1.setString(4, login.getRole());
			ps1.setString(5, login.getStatus());
			if(ps1.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(conn);
		}
		
		return false;
	}

	public String getRoleBySessionID(String sessionID){
		Connection conn = null;
		PreparedStatement ps1 = null;
		try{
			conn = ConnectionFactory.getConnection();
			ps1 = conn.prepareStatement("select role from login where sid=?");
			ps1.setString(1, sessionID);
			ResultSet set = ps1.executeQuery();
			if(set.next()){
				return set.getString("role");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(conn);
		}
		return null;
	}
	
	public String getUsernameBySessionID(String sessionID){
		Connection conn = null;
		PreparedStatement ps1 = null;
		try{
			conn = ConnectionFactory.getConnection();
			ps1 = conn.prepareStatement("select username from login where sid=?");
			ps1.setString(1, sessionID);
			ResultSet set = ps1.executeQuery();
			if(set.next()){
				return set.getString("username");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(conn);
		}
		return null;
	}
	
	
	public boolean insertSessionID(String id, String sessionID){
		Connection conn = null;
		PreparedStatement ps1 = null;
		try{
			conn = ConnectionFactory.getConnection();
			ps1 = conn.prepareStatement("update login set sid=? where username=?");
			ps1.setString(1, sessionID);
			ps1.setString(2, id);
			if(ps1.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(conn);
		}
		
		return false;
	}
	
	public void deleteSessionID(String sessionID){
		 Connection connection = null;
	        PreparedStatement p1 = null;
	        try {
	            connection = ConnectionFactory.getConnection();
	            p1 = connection.prepareStatement("update login set sid=? where sid=?");
	            p1.setNull(1, Types.VARCHAR);
	            p1.setString(2, sessionID);
	            if (p1.executeUpdate() == 1) {
	            } else {
	                System.out.println("Logout Not Complete");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            ConnectionFactory.close(connection);
	        }
	}
	
	public static void main(String[] args) {
		LoginDAO dao = new LoginIMPL();
		Login l = new Login();
		l.setPassword("deep");
		l.setUsername("DC01");
		l.setRole("ROLE_DC_CSE");
		//String satl = BCrypt.gensalt();
		l.setSalt(null);
		l.setStatus("active");
		dao.saveNewLogin(l);
		
	}
	
}
