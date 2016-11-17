package Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import Model.ExecuteForNoReturn;
import Model.ExecuteForResultSet;

/*
 * Controls action for Moderator
 * related actions defined here
 */
public class Moderator extends Member{

	Moderator(String email1, String password){
		super(email1, password);
	}
	
	public void createForum(String topic, String summary){
		try{
			ExecuteForNoReturn obj = new ExecuteForNoReturn();
			String query = "{CALL spCreateForum(?, ?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, topic);
			cs.setString(2, summary);
			cs.setString(3, this.get_user_name());
			obj.Execute(cs);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void delete_forum(int forum_id){
		
		try{
			ExecuteForNoReturn obj = new ExecuteForNoReturn();
			String query = "{CALL spDeleteForum(?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setInt(1, forum_id);
			cs.setString(2, this.get_user_name());
			obj.Execute(cs);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}