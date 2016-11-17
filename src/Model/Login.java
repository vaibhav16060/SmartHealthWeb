package Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import Model.ExecuteForBooleanReturn;
import Model.ExecuteForResultSet;

/*
 * this controls all the login relate action, called by LoginController only
 */
public class Login {
	
	String password, email1, usertype; 
	boolean status;
	Member doLogin(String email1, String password){
		
		ExecuteForBooleanReturn obj = new ExecuteForBooleanReturn();
		ExecuteForResultSet objRS = new ExecuteForResultSet();
		Member m = null;
		this.password = password;
		this.email1 = email1;
		try{
			String query = "{CALL spCheckLoginDetails(?,?)}";
			CallableStatement cs = objRS.getContext().prepareCall(query);
			cs.setString(1, email1);
			cs.setString(2, password);
			ResultSet tf = objRS.Execute(cs);
			if(tf.next()){
				int x = tf.getInt(1);
				if(x == 1)
					status = true;
				else
					status = false;
			}
			if(status == true){
				query = "{CALL spGetLoginData(?,?)}";
				cs = objRS.getContext().prepareCall(query);
				cs.setString(1, email1);
				cs.setString(2, password);
				ResultSet rs = objRS.Execute(cs);
				if(rs.next()){
					usertype = rs.getString(16);
				}
				
				if(usertype.equals("Moderator")){
					m = new Moderator(email1, password);
				}
				else if(usertype.equals("EndUser")){
					m = new EndUser(email1, password);
				}
				else if(usertype.equals("Administrator")){
					m = new Administrator(email1, password);
				}
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return m;
	}
}
