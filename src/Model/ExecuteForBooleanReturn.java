package Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public class ExecuteForBooleanReturn extends DataConnector{
	
	public ExecuteForBooleanReturn(){
		ConnectToDataBase();
	}
	
	public boolean Execute(CallableStatement cs){
		
		try{
			ResultSet rs = cs.executeQuery();
			if(rs.next()){
				if(rs.getInt(1) == 0)
					return false;
				else
					return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
