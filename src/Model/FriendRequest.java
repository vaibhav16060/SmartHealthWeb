package Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import Model.ExecuteForNoReturn;
import Model.ExecuteForResultSet;


/*
 * 
 * class for sending the Friend table maintenance
 * Responsible for sending friend requests and accepting them
 * 
 */

public class FriendRequest {
	public void send_request(String from_user_name, String to_user_name){
		try{
			ExecuteForNoReturn obj = new ExecuteForNoReturn();
			String query = "{CALL spSendFriendRequest(?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, from_user_name);
			cs.setString(2, to_user_name);
			obj.Execute(cs);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void unfriend_person(String from_user_name, String to_user_name){
		try{
			ExecuteForNoReturn obj = new ExecuteForNoReturn();
			String query = "{CALL spUnfriend(?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, from_user_name);
			cs.setString(2, to_user_name);
			obj.Execute(cs);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void accept_request(String from_user_name, String to_user_name){
		try{
			ExecuteForNoReturn obj = new ExecuteForNoReturn();
			String query = "{CALL spAcceptRequest(?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, from_user_name);
			cs.setString(2, to_user_name);
			obj.Execute(cs);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void reject_request(String from_user_name, String to_user_name){
		try{
			ExecuteForNoReturn obj = new ExecuteForNoReturn();
			String query = "{CALL spRejectRequest(?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, from_user_name);
			cs.setString(2, to_user_name);
			obj.Execute(cs);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String[][] all_sent_requests(String from_user_name){
		String[][] request_status = null;
		try{
			ExecuteForResultSet obj = new ExecuteForResultSet();
			String query = "{CALL spSentRequestDetails(?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, from_user_name);
			ResultSet rs = obj.Execute(cs);
			int rows = 0;
			while(rs.next()){
				rows++;
			}
			query = "{CALL spSentRequestDetails(?)}";
			cs = obj.getContext().prepareCall(query);
			cs.setString(1, from_user_name);
			rs = obj.Execute(cs);
			if(rows > 0)
				request_status = new String[rows][2];
			int i = -1;
			while(rs.next()){
				i++;
				request_status[i][0] = rs.getString(1);
				request_status[i][1] = rs.getString(2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return request_status;
	}
	
	public String[][] all_available_people(String from_user_name){
		String[][] all_people = null;
		try{
			ExecuteForResultSet obj = new ExecuteForResultSet();
			String query = "{CALL spAllAvailableForUser(?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, from_user_name);
			ResultSet rs = obj.Execute(cs);
			int rows = 0;
			while(rs.next()){
				rows++;
			}
			query = "{CALL spAllAvailableForUser(?)}";
			cs = obj.getContext().prepareCall(query);
			cs.setString(1, from_user_name);
			rs = obj.Execute(cs);
			if(rows > 0)
				all_people = new String[rows][2];
			int i = -1;
			while(rs.next()){
				i++;
				all_people[i][0] = rs.getString(1);
				all_people[i][1] = rs.getString(2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return all_people;
	}
	
	public String[][] all_recieved_requests(String for_user_name){
		String[][] all_people = null;
		try{
			ExecuteForResultSet obj = new ExecuteForResultSet();
			String query = "{CALL spAllRecievedRequests(?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, for_user_name);
			ResultSet rs = obj.Execute(cs);
			int size = 0;
			while(rs.next()){
				size++;
			}
			query = "{CALL spAllRecievedRequests(?)}";
			cs = obj.getContext().prepareCall(query);
			cs.setString(1, for_user_name);
			rs = obj.Execute(cs);
			if(size > 0)
				all_people = new String[size][2];
			int i = -1;
			while(rs.next()){
				i++;
				all_people[i][0] = rs.getString(1);
				all_people[i][1] = rs.getString(2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return all_people;
	}
	
	public String[] get_all_friends_list(String for_user_name){
		String[] all_people = null;
		try{
			ExecuteForResultSet obj = new ExecuteForResultSet();
			String query = "{CALL spGetAllFriends(?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, for_user_name);
			ResultSet rs = obj.Execute(cs);
			int size = 0;
			while(rs.next()){
				size++;
			}
			query = "{CALL spGetAllFriends(?)}";
			cs = obj.getContext().prepareCall(query);
			cs.setString(1, for_user_name);
			rs = obj.Execute(cs);
			if(size > 0)
				all_people = new String[size];
			int i = -1;
			while(rs.next()){
				i++;
				all_people[i] = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return all_people;
	}
}
