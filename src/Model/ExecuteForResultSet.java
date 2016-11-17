package Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public class ExecuteForResultSet extends DataConnector{
	
	public ExecuteForResultSet(){
		ConnectToDataBase();
	}
	
	public ResultSet Execute(CallableStatement cs){
		ResultSet rs = null;
		try{
			rs = cs.executeQuery();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
}
