package Model;

import java.sql.CallableStatement;

public class ExecuteForNoReturn extends DataConnector{
	
	public ExecuteForNoReturn(){
		ConnectToDataBase();
	}
	
	public void Execute(CallableStatement cs){
		
		try{
			cs.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
