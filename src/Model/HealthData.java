package Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import Model.ExecuteForNoReturn;
import Model.ExecuteForResultSet;
/*
 * this part is related to entering any health related data
 * the datum or property are entered here
 */
public class HealthData {
	
	public void insert_into_property(String name, String description){
		ExecuteForNoReturn obj = new ExecuteForNoReturn();
		try{
			String query = "{CALL spInsertProperty(?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, name);
			cs.setString(2, description);
			obj.Execute(cs);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String[] get_all_properties(){
		String all_prop[] = null;
		ExecuteForResultSet obj = new ExecuteForResultSet();
		try{
			String query = "{CALL spGetProperty()}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			ResultSet rs = obj.Execute(cs);
			int count = 0;
			while(rs.next()){
				count++;
			}
			if(count > 0){
				all_prop = new String[count];
				int i = 0;
				rs = obj.Execute(cs);
				while(rs.next()){
					all_prop[i] = rs.getString(1);
					i++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return all_prop;
	}
	
	public void insert_into_datum(String name, int prop_id, String value){
		ExecuteForNoReturn obj = new ExecuteForNoReturn();
		try{
			String query = "{CALL spInsertDatum(?, ?, ?)}";
			CallableStatement cs = obj.getContext().prepareCall(query);
			cs.setString(1, name);
			cs.setInt(2, prop_id);
			cs.setString(3, value);
			obj.Execute(cs);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
