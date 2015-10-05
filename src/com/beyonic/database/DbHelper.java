package com.beyonic.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class DbHelper {
	static ResultSet rs;
	static Connection conn = null;
	public DbHelper(){
		
		
	}
	
	public static void startConnection()throws SQLException{
	
			conn = DBUtil.getConnection();
		
	}
	
	
	public static void CloseConnection() throws SQLException{
		
			conn.close();
	
	} 
	
	public static boolean insertUser(User user) throws SQLException{
		boolean insertSuccess = false;
		String query = "INSERT INTO users(password,full_names,phone,email,verification_code,activation)"
				+ "VALUES(?,?,?,?,?,?) ";
		
		startConnection();
		
		try(
				
				PreparedStatement statement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				
				) {
			
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getFull_name());
			statement.setString(3, user.getPhone());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getVerification_code());
			statement.setBoolean(6, user.isActivation());
			
			int success = statement.executeUpdate();
			
			if(success == 1 ){
				rs = statement.getGeneratedKeys();
				rs.next();
				int generated_id = rs.getInt(1);
				user.setId(generated_id);
				insertSuccess = true;
			}else{
				
				System.err.println("No rows affected retry");
				insertSuccess = false;
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(rs != null) rs.close();
			CloseConnection();
		}
		return insertSuccess;
	}
	
	public static User getUser(String email) throws SQLException{
		
		String query = "SELECT * FROM users WHERE email = ?";
		
			startConnection();
	
		
		try(
				PreparedStatement statement = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				
				) {
			statement.setString(1, email);
			rs = statement.executeQuery();
			rs.last();
			if(rs.getRow() == 1){
				
				return new User(rs.getString("email"), rs.getString("phone"), rs.getString("full_names"), rs.getString("password"), rs.getString("verification_code"),rs.getBoolean("activation"));
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(rs != null) rs.close();
			CloseConnection();
		}
		return null;
	}
	
public static boolean activateUser(User user) throws SQLException{
		
		String query = "UPDATE users SET activation=? WHERE phone= ?";
		
		startConnection();
		
		try(
				
				PreparedStatement statement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
				
				) {
			statement.setBoolean(1,true);
			statement.setString(2,user.getPhone());
			int success = statement.executeUpdate();
			
			if(success == 1 ){
				return true;
			}else{
				return false;
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(rs != null) rs.close();
			CloseConnection();
		}
		
		return false;
		
	}

public static boolean deActivateUser(User user) throws SQLException{
	
	String query = "UPDATE users SET activation=? WHERE phone= ?";
	
	startConnection();
	
	try(
			
			PreparedStatement statement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			) {
		statement.setBoolean(1,false);
		statement.setString(2,user.getPhone());
		int success = statement.executeUpdate();
		
		if(success == 1 ){
			return true;
		}else{
			return false;
		}
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally{
		if(rs != null) rs.close();
		CloseConnection();
	}
	
	return false;
	
}

	
}
