package Model;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.ExecuteForNoReturn;
import Model.ExecuteForResultSet;

public class EndUser extends Member{

	EndUser(String email1, String password){
		super(email1, password);
	}
	
	protected void push_rating(int rating, String post_username, String post_time_created){
		ExecuteForNoReturn obj = new ExecuteForNoReturn();
		try{
			String query = "{CALL spInsertRating(?, ?, ?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, post_username);
			cs.setString(2, post_time_created);
			cs.setString(3, this.get_user_name());
			cs.setInt(4, rating);
			obj.Execute(cs);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected double get_rating(String post_username, 
			String post_time_created){
		double rating = 0.0;
		ExecuteForResultSet obj = new ExecuteForResultSet();
		try{
			String query = "{CALL spGetAvgRating(?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, post_username);
			cs.setString(2, post_time_created);
			ResultSet rs = obj.Execute(cs);
			if(rs.next()){
				rating = rs.getDouble(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return rating;
	}
	
	protected boolean if_rating_by_user_exists_on(String post_username, 
			String post_time_created){
		boolean ret = true;
		ExecuteForResultSet obj = new ExecuteForResultSet();
		try{
			String query = "{CALL spCheckRatingExists(?, ?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, post_username);
			cs.setString(2, post_time_created);
			cs.setString(3, this.get_user_name());
			ResultSet rs = obj.Execute(cs);
			if(rs.next()){
				int x = rs.getInt(1);
				if(x == 1)
					ret = true;
				else
					ret = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	//get dd string array as forum data for display
	protected String[] get_forum_data_for_display(int id){
		String[] forum_data = null;
		ExecuteForResultSet obj = new ExecuteForResultSet();
		try{
			String query = "{CALL spGetForumDataAtLogin(?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setInt(1, id);
			ResultSet rs = obj.Execute(cs);
			int count = 0;
			while(rs.next())
				count ++;
			if(count > 0){
				rs = obj.Execute(cs);
				forum_data = new String[2];
				if(rs.next()){
					forum_data[0] = rs.getString(1);
					forum_data[1] = rs.getString(2);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return forum_data;
	}
	
	//get all posts as an array list of Post
	protected ArrayList<Post> fetch_all_posts(int forum_id){
		int i;
		Post p;
		ArrayList<Post> al = null;
		String[] post_detail = new String[3];
		ExecuteForResultSet obj = new ExecuteForResultSet();
		try{
			String query = "{CALL spGetAllPostsById(?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setInt(1, forum_id);
			ResultSet rs = obj.Execute(cs);
			int count = 0;
			while(rs.next()){
				count++;
			}
			if(count > 0){
				//add all the posts to the array list
				rs = obj.Execute(cs);
				al = new ArrayList<Post>();
				while(rs.next()){
					post_detail[0] = rs.getString(1);
					post_detail[1] = rs.getString(2);
					post_detail[2] = rs.getString(3);
					p = new Post(forum_id, post_detail);
					al.add(p);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return al;
	}
	
	
	//see all the health data, return string dd array
	protected String[][] see_health_data(String user_id){
		
		ExecuteForResultSet obj = new ExecuteForResultSet();
		String health_data[][] = null;
		try{
			String query = "{CALL spGetHealthData(?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, user_id);
			ResultSet rs = obj.Execute(cs);
			int count = 0;
			while(rs.next()){
				count++;
			}
			int i = 0;
			if(count > 0){
				rs = obj.Execute(cs);
				health_data = new String[count][2];
				while(rs.next()){
					health_data[i][0] = rs.getString(1);
					health_data[i][1] = rs.getString(2);
					i++;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return health_data;
	}
	
	
	//insert the post
	protected void insert_post(int f_id, String post, String user_name){
		ExecuteForNoReturn obj = new ExecuteForNoReturn();
		try{
			String query = "{CALL spInsertPost(?, ?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setInt(1, f_id);
			cs.setString(2, post);
			cs.setString(3, user_name);
			obj.Execute(cs);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
