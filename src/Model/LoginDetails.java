package Model;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.ResultSet;

import Model.ExecuteForBooleanReturn;
import Model.ExecuteForNoReturn;
import Model.ExecuteForResultSet;

/*
 * 
 * this part relates to functions called during login
 * An object of this will be created for all the users who log into the application
 * The constructor checks the login details and sets status
 * To check that there was a successful login first
 * call object.is_login_successful()
 * 
 */

public class LoginDetails {

	private Member me;
	private boolean status = true;
	private ArrayList<Post> posts = null;
	private Administrator a; EndUser e; Moderator m;
	public LoginDetails(String email1, String password){
		
		me = new Login().doLogin(email1, password);
		if(me == null){
			status = false;
		}
		else if(me.get_user_type().equals("Administrator")){
			a = (Administrator)me;
			status = true;
		}
		else if(me.get_user_type().equals("Moderator")){
			m = (Moderator)me;
			status = true;
		}
		else if(me.get_user_type().equals("EndUser")){
			e = (EndUser)me;
			status = true;
		}
	}
	
	public void update_edu_details_moderator(String edu_details){
		m.update_qualification(edu_details);
	}
	
	public boolean if_login_successful(){
		return status;
	}
	
	public boolean if_rating_by_user_exists_on(String post_username, 
			String post_time_created){
		boolean ret = false;
		ret = e.if_rating_by_user_exists_on(post_username, post_time_created);
		return ret;
		
	}
	
	public double get_rating_for_post(String post_username, 
			String post_time_created){
		return e.get_rating(post_username, post_time_created);
	}
	
	public void insert_rating(int rating, String post_username, 
			String post_time_created){
		e.push_rating(rating, post_username, post_time_created);
	}
	
	public String[] get_image_urls(){
		String s[] = new String[3];
		s[0] = me.get_user_name() + "\\" + me.get_image_url1();
		s[1] = me.get_user_name() + "\\" + me.get_image_url2();
		s[2] = me.get_user_name() + "\\" + me.get_image_url3();
		return s;
	}
	
	public void set_image_urls(boolean image_urls[], String image[]){
		
		if(image_urls[0] == true){
			me.set_image_url1(image[0]);
		}
		if(image_urls[1] == true){
			me.set_image_url2(image[1]);
		}
		if(image_urls[2] == true){
			me.set_image_url3(image[2]);
		}
		me.update_data();
	}
	
	public boolean if_password_matches(String pass){
		if(me.get_password().equals(pass)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String get_running_status(){
		if(me == null){
			return "NA";
		}
		return me.get_running_status();
	}
	
	public String get_name(){
		return me.get_name();
	}
	
	public String get_first_name(){
		return me.get_first_name();
	}
	
	public String get_last_name(){
		return me.get_last_name();
	}
	
	public String get_user_type(){
		return me.get_user_type();
	}
	
	public void deactivate_account(){
		me.deactivate_account();
	}
	
	public void update_data(){
		me.update_data();
	}
	
	public String get_user_name(){
		return me.get_user_name();
	}
	
	public void set_name(String first_name, String last_name){
		me.set_name(first_name, last_name);
	}
	
	public void set_about_me(String about_me){
		me.set_about_me(about_me);
	}
	
	public void set_address(String postal_addr){
		me.set_address(postal_addr);
	}
	
	public void set_password(String password){
		me.set_password(password);
	}
	
	public void set_phone_number(String phone_no){
		me.set_phone_number(phone_no);
	}
	
	public void reactivate_account(){
		me.reactivate_account();
	}
	
	public void create_forum(String topic, String summary){
		String name = me.get_name();
		m.createForum(topic, summary);
	}
	
	public String[][] get_all_forum_list(){
		return me.get_all_forum_list();
	}
	
	public void delete_forum(int forum_id){
		m.delete_forum(forum_id);
	}
	
	public String[] get_forum_data_for_display(int id){
		return e.get_forum_data_for_display(id);
	}
	
	public String[][] fetch_all_posts(int forum_id){
		posts = e.fetch_all_posts(forum_id);
		String [][]all_post = null;
		Post p;
		if(posts != null){
			int count = posts.size(), i = 0;
			all_post = new String[count][4];
			Iterator<Post> itr = posts.iterator();
			while(itr.hasNext()){
				p = (Post) itr.next();
				all_post[i][0] = p.getUsername();
				all_post[i][1] = p.getTimeCreated();
				all_post[i][2] = p.getForumId();
				all_post[i][3] = p.getTextEntry();
				i++;
			}
		}
		return all_post;
	}

	public String[][] get_comments_for_post(int post_no) {
		
		int i = 0;
		Post p = null;
		if(posts != null){
			if(post_no >  posts.size())
				return null;
			Iterator<Post> itr = posts.iterator();
			while(itr.hasNext()){
				p = (Post)itr.next();
				if(i == post_no){
					break;
				}
				i++;
			}
			return p.getComments();
		}
		return null;
	}
	
	public void insert_comment_on_post(int post_no, String comment, String u_name){
		int i = 0;
		Post p = null;
		if(posts != null){
			if(post_no >  posts.size()){}
			else{
				Iterator<Post> itr = posts.iterator();
				while(itr.hasNext()){
					p = (Post)itr.next();
					if(i == post_no){
						break;
					}
					i++;
				}
				p.push_comment(comment,u_name);
			}
		}
	}
	
	public String[][] see_health_data(String user_id){
		return e.see_health_data(user_id);
	}
	
	public void insert_post(int f_id, String post, String user_name){
		e.insert_post(f_id, post, user_name);
	}
}
