/**
 * 
 */
package com.samer.iam.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.samer.iam.datamodel.Identity;
import com.samer.iam.exceptions.DaoInitializationException;

/**
 * @author samer
 *
 */
public class IdentityJDBCDAO {

	private Connection currentConnection;

	/**
	 * 
	 */
	public IdentityJDBCDAO() throws DaoInitializationException{
		try {
			getConnection();
		} catch (SQLException e) {
			DaoInitializationException die = new DaoInitializationException();
			die.initCause(e);
			throw die;
		}
	}	
	
	/**
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		try {
			this.currentConnection.getSchema();
		} catch (Exception e) {
			// TODO read those information from a file
			String user = "samer";
			String password = "samer";
			String connectionString = "jdbc:derby://localhost:1527/IAM;create=true";
			this.currentConnection = DriverManager.getConnection(connectionString, user, password);
		}
		return this.currentConnection;
	}
	
	private void releaseResources() {
		try {
			this.currentConnection.close();
		} catch (Exception e) {
			//TODO trace Exception
		}
	}
	
	public List<Identity> readAllIdentities() throws SQLException {
	
		List<Identity> identities = new ArrayList<Identity>();

		Connection connection = getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from IDENTITIES");
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			int uid = rs.getInt("IDENTITY_ID");
			String firstName = rs.getString("IDENTITY_FIRSTNAME");
			String lastName = rs.getString("IDENTITY_LASTNAME");
			String email = rs.getString("IDENTITY_EMAIL");
			Date date = rs.getDate("IDENTITY_BIRTHDATE");
			Identity identity = new Identity(String.valueOf(uid), firstName, lastName, email, date);
			identities.add(identity);
		}
		releaseResources();
		return identities;
	}
	/*in case the user is entering a parameter (in this case while updating an identity.. it will show the
	info just for this identity*/
	public List<Identity> readAllIdentities(int id) throws SQLException {
		
		List<Identity> identities = new ArrayList<Identity>();

		Connection connection = getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from IDENTITIES WHERE IDENTITY_ID = ?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			int uid = rs.getInt("IDENTITY_ID");
			String firstName = rs.getString("IDENTITY_FIRSTNAME");
			String lastName = rs.getString("IDENTITY_LASTNAME");
			String email = rs.getString("IDENTITY_EMAIL");
			Date date = rs.getDate("IDENTITY_BIRTHDATE");
			Identity identity = new Identity(String.valueOf(uid), firstName, lastName, email, date);
			identities.add(identity);
		}
		releaseResources();
		return identities;
	}
	
	/**
	 * write an identity in the database
	 * @param identity
	 * @throws SQLException
	 */
	public void write(Identity identity) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "INSERT INTO IDENTITIES(IDENTITY_FIRSTNAME, IDENTITY_LASTNAME, IDENTITY_EMAIL, IDENTITY_BIRTHDATE) VALUES(?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getFirstname());
		pstmt.setString(2, identity.getLastname());
		pstmt.setString(3, identity.getEmail());
		pstmt.setDate(4, new java.sql.Date(identity.getBirthdate().getTime()));

		pstmt.execute();
		releaseResources();
	}
	
	/**
	 * update user's credentials in the database
	 * @param identity
	 * @throws SQLException
	 */
	public void update(Identity identity) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "UPDATE IDENTITIES SET IDENTITY_FIRSTNAME=?, IDENTITY_LASTNAME=?, IDENTITY_EMAIL=?, IDENTITY_BIRTHDATE=? WHERE IDENTITY_ID=?";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getFirstname());
		pstmt.setString(2, identity.getLastname());
		pstmt.setString(3, identity.getEmail());
		pstmt.setDate(4, new java.sql.Date(identity.getBirthdate().getTime()));
		pstmt.setInt(5, Integer.parseInt(identity.getUid()));

		pstmt.execute();
		releaseResources();
	}

	/**
	 * delete a user in the database
	 * @param identity
	 * @throws SQLException
	 */
	public void delete(Identity identity) throws SQLException {
		Connection connection = getConnection();

		String sqlInstruction = "DELETE FROM IDENTITIES WHERE IDENTITY_ID=?";
		PreparedStatement pstmt = connection.prepareStatement(sqlInstruction);
		pstmt.setString(1, identity.getUid());

		pstmt.execute();
		releaseResources();	
	}

}
