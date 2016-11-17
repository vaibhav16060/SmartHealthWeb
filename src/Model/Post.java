package Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import Model.ExecuteForNoReturn;
import Model.ExecuteForResultSet;
/*
 * controls all the actions for Post
 */
public class Post {
	
	int ForumId;
	String username, timecreated, textentry;
	String[][] comments = null;
	int rating;
	
	Post(int forum_id, String[] arr){
		
		ForumId = forum_id;
		username = arr[0];
		timecreated = arr[1];
		textentry = arr[2];
		//logic to get all comments in the comments array
		ExecuteForResultSet obj = new ExecuteForResultSet();
		try{
			String query = "{CALL spGetAllRelatedComments(?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, username);
			cs.setString(2, timecreated);
			ResultSet rs = obj.Execute(cs);
			int count = 0;
			while(rs.next()){
				count++;
			}
			if(count > 0){
				rs = obj.Execute(cs);
				comments = new String[count][6];
				int i = 0;
				while(rs.next()){
					comments[i][0] = rs.getString(1);
					comments[i][1] = rs.getString(2);
					comments[i][2] = rs.getString(3);
					comments[i][3] = rs.getString(4);
					comments[i][4] = rs.getString(5);
					comments[i][5] = rs.getString(6);
					i++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//logic to calculate the combined rating
	}
	
	protected String getForumId(){
		return (ForumId+"");
	}
	
	protected String getTimeCreated(){
		return timecreated;
	}
	
	protected String getTextEntry(){
		return textentry;
	}
	
	protected String getUsername(){
		return username;
	}
	
	protected String[][] getComments(){
		return comments;
	}
	
	protected void push_comment(String comment, String c_username){
		ExecuteForNoReturn obj = new ExecuteForNoReturn();
		try{
			String query = "{CALL spInsertCommentForPost(?, ?, ?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, username);
			cs.setString(2, timecreated);
			cs.setString(3, c_username);
			cs.setString(4, comment);
			obj.Execute(cs);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
