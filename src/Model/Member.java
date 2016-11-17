package Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import Model.ExecuteForBooleanReturn;
import Model.ExecuteForNoReturn;
import Model.ExecuteForResultSet;
/*
 * member abstract class that defines all the appropriate functions common to all
 * function names are self explanatory
 */
public abstract class Member {
	private String email1, password, username, email2, first_name, last_name, about_me, photoURL1, photoURL2, photoURL3, 
	street_no, street_name, MajorMuniciality, 
	GoverningDistrict, PostalArea, phone, usertype, deactive_status;
	private int UserTypeId, Status;
	private boolean status = true;
	protected Member(String email1, String password){
		
		ExecuteForBooleanReturn obj = new ExecuteForBooleanReturn();
		ExecuteForResultSet objRS = new ExecuteForResultSet();
		this.password = password;
		this.email1 = email1;
		this.deactive_status = "";
		try{
			String query;
			CallableStatement cs;
			if(status == true){
				//get a result set containing all the values and here use it to assign all the data
				query = "{CALL spGetLoginData(?,?)}";
				cs = objRS.getContext().prepareCall(query);
				cs.setString(1, email1);
				cs.setString(2, password);
				ResultSet rs = objRS.Execute(cs);
				if(rs.next()){
					username = rs.getString(1);
					email2 = rs.getString(3);
					first_name = rs.getString(4);
					last_name = rs.getString(5);
					about_me = rs.getString(6);
					photoURL1 = rs.getString(7); 
					photoURL2 = rs.getString(8); 
					photoURL3 = rs.getString(9); 
					street_no = rs.getString(10); 
					street_name = rs.getString(11); 
					MajorMuniciality = rs.getString(12); 
					GoverningDistrict = rs.getString(13); 
					PostalArea = rs.getString(14);
					usertype = rs.getString(16);
					Status = rs.getInt(15);
					phone = rs.getString(17);
				}
				else{
					status = false;
				}
				if(Status == 0){ //is the user id deactivated
					status = false;
					this.deactive_status = "Deactivated";
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected boolean if_login_successful(){
		return status;
	}	
	
	public String get_image_url1(){
		return photoURL1;
	}
	
	public String get_image_url2(){
		return photoURL2;
	}
	
	public String get_image_url3(){
		return photoURL3;
	}
	
	public void set_image_url1(String s){
		photoURL1 = s;
	}
	
	public void set_image_url2(String s){
		photoURL2 = s;
	}
	
	public void set_image_url3(String s){
		photoURL3 = s;
	}
	
	protected void set_name(String first_name, String last_name){
		this.first_name = first_name;
		this.last_name = last_name;
	}
	protected void set_about_me(String about_me){
		this.about_me= about_me;
	}
	protected void set_address(String street_no){
		this.street_no = street_no;
	}
	protected void set_password(String password){
		this.password = password;
	}
	
	protected String get_password(){
		return this.password;
	}
	
	protected String get_first_name(){
		return this.first_name;
	}
	
	protected String get_last_name(){
		return this.last_name;
	}
	protected void set_phone_number(String phone){
		this.phone = phone;
	}
	protected String get_name(){
		return (first_name + " " + last_name);
	}
	
	protected String get_running_status(){
		return deactive_status;
	}
	protected void deactivate_account(){
		Status = 0;
	}
	protected void reactivate_account(){
		Status = 1;
	}
	protected String get_user_name(){
		return username;
	}
	
	protected String get_user_type(){
		return usertype;
	}
	
	protected void update_data(){
		ExecuteForNoReturn obj = new ExecuteForNoReturn();
		try{
			String query = "{CALL spUpdateData(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, password);
			cs.setString(2, username);
			cs.setString(3, email2);
			cs.setString(4, first_name);
			cs.setString(5, last_name);
			cs.setString(6, about_me);
			cs.setString(7, photoURL1);
			cs.setString(8, photoURL2);
			cs.setString(9, photoURL3);
			cs.setString(10, street_no);
			cs.setString(11, street_name);
			cs.setString(12, MajorMuniciality);
			cs.setString(13, GoverningDistrict);
			cs.setString(14, PostalArea);
			cs.setString(15, phone);
			cs.setString(16, usertype);
			cs.setInt(17, Status);
			obj.Execute(cs);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected String[][] get_all_forum_list(){
		
		String[][] all_forums = null;
		try{
			ExecuteForResultSet obj = new ExecuteForResultSet();
			String query = "{CALL spGetAllForums()}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			ResultSet rs = obj.Execute(cs);
			int rows = 0;
			while(rs.next()){
				rows++;
			}
			query = "{CALL spGetAllForums()}";
			cs = obj.getContext().prepareCall(query);
			rs = obj.Execute(cs);
			if(rows > 0)
				all_forums = new String[rows][3];
			int i = -1;
			while(rs.next()){
				i++;
				all_forums[i][0] = rs.getString(1);
				all_forums[i][1] = rs.getString(2);
				all_forums[i][2] = rs.getString(3);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return all_forums;
	}
	
	protected void update_qualification(String qualification){
		try{
			ExecuteForNoReturn obj = new ExecuteForNoReturn();
			String query = "{CALL spAddQualifications(?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, this.get_user_name());
			cs.setString(2, qualification);
			obj.Execute(cs);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
