package Model;

import java.sql.CallableStatement;

import Model.ExecuteForBooleanReturn;
import Model.ExecuteForNoReturn;

/*
 * Inner class for the Registration Process
 * 
 * register_user() => Putting values into the User table
 * is_unique_user_id() => finding if the user_id entered by user exists in the database
 * is_unique_email1(String email1) => finding if the primary email exists in the database
 * set_user_type_as(String type, String username, String phone) => for setting the correct user type
 * 
 */

public class Register {
	public void register_user(String username, String password, 
			String email1, String email2, String first_name, 
			String last_name, String about_me, 
			String photoURL1, String photoURL2, String photoURL3, 
			String street_no, String street_name, String MajorMuniciality, 
			String GoverningDistrict, String PostalArea, int UserTypeId, int Status){
		
			try{
				ExecuteForNoReturn obj = new ExecuteForNoReturn();
				String query = "{CALL spAddUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
				CallableStatement cs = obj.getContext().prepareCall(query);
				cs.setString(1, username);
				cs.setString(2, password);
				cs.setString(3, email1);
				cs.setString(4, email2);
				cs.setString(5, first_name);
				cs.setString(6, last_name);
				cs.setString(7, about_me);
				cs.setString(8, photoURL1);
				cs.setString(9, photoURL2);
				cs.setString(10, photoURL3);
				cs.setString(11, street_no);
				cs.setString(12, street_name);
				cs.setString(13, MajorMuniciality);
				cs.setString(14, GoverningDistrict);
				cs.setString(15, PostalArea);
				cs.setInt(16, UserTypeId);
				cs.setInt(17, Status);
				obj.Execute(cs);
			}
			catch(Exception e){
				e.printStackTrace();
			}
	    }
	
	public boolean exists_user_id(String user_id){
		
			boolean res = true;
			try{
				String query = "{CALL spIfExistsUserId(?)}";
				ExecuteForBooleanReturn obj = new ExecuteForBooleanReturn();
				CallableStatement cs = obj.getContext().prepareCall(query);
				cs.setString(1, user_id);
				return obj.Execute(cs);
			}catch(Exception e){
				e.printStackTrace();
			}
			return res;
		}
	
	public boolean is_unique_email1(String email1){
		
			boolean res = true;
			try{
				ExecuteForBooleanReturn obj = new ExecuteForBooleanReturn();
				String query = "{CALL spIfExistsEmailId(?)}";
				CallableStatement cs = obj.getContext().prepareCall(query);
				cs.setString(1, email1);
				return (!obj.Execute(cs));
			}catch(Exception e){
				e.printStackTrace();
			}
			return res;
		}
	
	public void set_user_type_as(String type, String username, String phone){
		
			String query;
			CallableStatement cs;
			try{
				ExecuteForNoReturn obj = new ExecuteForNoReturn();
				if(type.equals("user")){
					query = "{CALL spAddEndUser(?)}";
					cs = obj.getContext().prepareCall(query);
					cs.setString(1, username);
				}
				
				else if(type.equals("admin")){
					query = "{CALL spAddAdministrator(?, ?)}";
					cs = obj.getContext().prepareCall(query);
					cs.setString(1, username);
					cs.setString(2, phone);
				}
				else{ //this is moderator
					query = "{CALL spAddModerator(?, ?)}";
					cs = obj.getContext().prepareCall(query);
					cs.setString(1, username);
					cs.setString(2, phone);
				}
				obj.Execute(cs);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
}
