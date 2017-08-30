package com.publication.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import com.publication.constants.BCrypt;
import com.publication.constants.LoginStatus;
import com.publication.dao.BookChapterDAO;
import com.publication.dao.BookDAO;
import com.publication.dao.ConferencePresentationDAO;
import com.publication.dao.ConferenceProceedingDAO;
import com.publication.dao.JournalDAO;
import com.publication.dao.LoginDAO;
import com.publication.dao.PatentDAO;
import com.publication.dao.TechnicalReportDAO;
import com.publication.database.ConnectionFactory;
import com.publication.model.Login;

public class LoginIMPL implements LoginDAO {

	public String getNewPassword(){

	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	 
	    System.out.println(generatedString);
	    return generatedString;
	}
	
	@Override
	public boolean addFaculty(String id, String name, String email, String contact){
		Connection conn = null;
		PreparedStatement ps1 = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps1 = conn.prepareStatement("insert into login (username,name, password, salt, role, status)  values (?,?,?,?,?,?)");
			String salt = BCrypt.gensalt();
			String password = getNewPassword();
			ps1.setString(1, id);
			ps1.setString(2, name);
			ps1.setString(3, BCrypt.hashpw(password, salt));
			ps1.setString(4, salt);
			ps1.setString(5, "ROLE_FACULTY");
			ps1.setString(6, "active");
			if (ps1.executeUpdate() > 0) {
				EmailService.sendEmail("NCU PUBLICATION PORTAL: LOGIN Credentials", "Your Publication Portal Login Credentials: Username:\""+id+"\" and password:\""+password+"\".", email);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(conn);
		}


		return false;
	}
	
	@Override
	public LoginStatus validateLogin(String username, String password, String role) {

		Login login = getLogin(username);
		System.out.println(login);
		if (login == null) {
			return LoginStatus.NO_SUCH_ACCOUNT_FOUND;
		}
		if (role.equals(login.getRole())) {
			if (login.getStatus().equals("active")) {
				System.out.println(BCrypt.hashpw(password, login.getSalt()) + " " + login.getPassword());

				if (login.getPassword().equals(BCrypt.hashpw(password, login.getSalt()))) {
					return LoginStatus.SUCCESS;
				} else {
					return LoginStatus.WRONG_PASSWORD;
				}
			} else {
				return LoginStatus.DEACTIVATED;
			}
		} else {
			return LoginStatus.NOT_AUTHORIZED;
		}

	}

	@Override
	public Login getLogin(String username) {
		Connection conn = null;
		PreparedStatement ps1 = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps1 = conn.prepareStatement("select * from login where username=?");
			System.out.println(username);
			ps1.setString(1, username);
			ResultSet set = ps1.executeQuery();
			if (set.next()) {
				Login login = new Login();
				login.setUsername(set.getString("username"));
				login.setPassword(set.getString("password"));
				login.setStatus(set.getString("status"));
				login.setRole(set.getString("role"));
				login.setSalt(set.getString("salt"));
				login.setName(set.getString("name"));
				login.setEmail(set.getString("email"));
				login.setContact(set.getString("contact"));
				return login;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	public boolean saveNewLogin(Login login) {
		Connection conn = null;
		PreparedStatement ps1 = null;
		System.out.println(login);
		try {
			conn = ConnectionFactory.getConnection();
			ps1 = conn
					.prepareStatement("insert into login (username, password, salt, role, status)  values (?,?,?,?,?)");
			String sall = BCrypt.gensalt();
			ps1.setString(1, login.getUsername());
			ps1.setString(2, BCrypt.hashpw(login.getPassword(), sall));
			ps1.setString(3, sall);
			ps1.setString(4, login.getRole());
			ps1.setString(5, login.getStatus());
			if (ps1.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(conn);
		}

		return false;
	}

	public String getRoleBySessionID(String sessionID) {
		Connection conn = null;
		PreparedStatement ps1 = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps1 = conn.prepareStatement("select role from login where sid=?");
			ps1.setString(1, sessionID);
			ResultSet set = ps1.executeQuery();
			if (set.next()) {
				return set.getString("role");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(conn);
		}
		return null;
	}

	public String getUsernameBySessionID(String sessionID) {
		Connection conn = null;
		PreparedStatement ps1 = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps1 = conn.prepareStatement("select username from login where sid=?");
			ps1.setString(1, sessionID);
			ResultSet set = ps1.executeQuery();
			if (set.next()) {
				return set.getString("username");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(conn);
		}
		return null;
	}

	public boolean insertSessionID(String id, String sessionID) {
		Connection conn = null;
		PreparedStatement ps1 = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps1 = conn.prepareStatement("update login set sid=? where username=?");
			ps1.setString(1, sessionID);
			ps1.setString(2, id);
			if (ps1.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(conn);
		}

		return false;
	}

	public void deleteSessionID(String sessionID) {
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

	public String forgotRequest(String username) throws IOException {
		Connection connection = null;
		PreparedStatement p1 = null;
		PreparedStatement p2 = null;
		String token = generateToken();
		String TIME_SERVER = "time-a.nist.gov";
		NTPUDPClient timeClient = new NTPUDPClient();
		InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
		TimeInfo timeInfo = timeClient.getTime(inetAddress);
		long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
		try {
			connection = ConnectionFactory.getConnection();
			p1 = connection.prepareStatement("update login set status='deactive',sid=? where username=?");
			p2 = connection.prepareStatement("insert into forgotreq (token, username, time) values (?,?,?)");
			p1.setNull(1, Types.VARCHAR);
			p1.setString(2, username);
			p2.setString(1, token);
			p2.setString(2, username);
			p2.setTimestamp(3, new Timestamp(returnTime));
			if (p1.executeUpdate() > 0 && p2.executeUpdate() > 0) {
				return token;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	
	public boolean reForgotRequest(String token, String username, Timestamp timestamp) throws IOException {
		Connection connection = null;
		PreparedStatement p1 = null;
		try {
			connection = ConnectionFactory.getConnection();
			p1 = connection.prepareStatement("update forgotreq set token=?, time=? where username=?");
			p1.setString(1, token);
			p1.setTimestamp(2, timestamp);
			p1.setString(3,  username);
			if (p1.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return false;
	}
	
	public boolean finishForgetPassword(String token, String password) {

		Connection connection = null;
		PreparedStatement p1 = null;
		PreparedStatement p2 = null;
		String username = forgotResponse(token)[0].toString();

		try {
			connection = ConnectionFactory.getConnection();
			p1 = connection.prepareStatement("update login set status='active', password=?, salt=? where username=?");
			String salt = BCrypt.gensalt();
			String hashedPassword = BCrypt.hashpw(password, salt);
			p1.setString(1, hashedPassword);
			p1.setString(2, salt);
			p1.setString(3, username);
			p2 = connection.prepareStatement("delete from forgotreq where token=? ");
			p2.setString(1, token);
			if (p1.executeUpdate() > 0 && p2.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}
		return false;
	}

	
	public String getEmailByUsername(String username) {
		Connection connection = null;
		PreparedStatement p1 = null;
		try {
			connection = ConnectionFactory.getConnection();
			p1 = connection.prepareStatement("select email from  login where username=?");
			p1.setString(1, username);
			ResultSet rs = p1.executeQuery();
			if (rs.next()) {
				return rs.getString("email");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	
	public Object[] forgotResponse(String token) {
		Connection connection = null;
		PreparedStatement p1 = null;
		try {
			connection = ConnectionFactory.getConnection();
			p1 = connection.prepareStatement("select username,time  from  forgotreq where token=?");
			p1.setString(1, token);
			ResultSet rs = p1.executeQuery();
			if (rs.next()) {
				return new Object[]{rs.getString("username"), rs.getTimestamp("time")};
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		return null;
	}

	

	
	public String generateToken() {
		return UUID.randomUUID().toString();
	}
	
	public int checkRejectedSum(String id){
		JournalDAO  dao1 = new JournalIMPL();
		PatentDAO dao2 = new PatentIMPL();
		BookDAO dao3 = new BooksIMPL();
		BookChapterDAO dao4 = new BookChapterIMPL();
		ConferencePresentationDAO dao5 = new ConferencePresentationIMPL();
		ConferenceProceedingDAO dao6 =  new ConferenceProceedingIMPL();
		TechnicalReportDAO dao7 = new TechnicalReportIMPL();
		
		return dao1.notificationRejectedJournal(id)+dao2.notificationRejectedPatents(id)+dao3.notificationRejectedBooks(id)+
				dao4.notificationRejectedBookChapters(id)+dao5.notificationRejectedConferencePresentations(id)+dao6.notificationRejectedConferenceProceedingss(id)
				+dao7.notificationRejectedTechnicalReports(id);
		}

	public static void main(String[] args) {
		LoginIMPL impl = new LoginIMPL();
		System.out.println(impl.checkRejectedSum("F001"));
	}
	
}
