package Testing;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.*;

import Model.EndUser;
import Model.FriendRequest;
import Model.HealthData;
import Model.LoginDetails;
import Model.EndUser;


public class TestCase {

	static Model.FriendRequest control;
	static Model.HealthData control2;
	static Model.EndUser control3;
	
	@BeforeClass
	public static void setupUpBeforeClass() {
		control = Mockito.mock(FriendRequest.class);
		control2 = Mockito.mock(HealthData.class);
		control3 = Mockito.mock(EndUser.class);
		
		Mockito.when(control.all_recieved_requests("tabh")).thenReturn(new String[][]{{"iotiriojt","Golf Singh"},{"vpande","Vaibhav Pande"}});
		Mockito.when(control.get_all_friends_list("shiva1")).thenReturn(new String[]{"tabh","poiuy","iotiriojt"});
		Mockito.when(control.get_all_friends_list("vpande")).thenReturn(new String[]{"tabh","shiva1","iotiriojt"});
		Mockito.when(control.get_all_friends_list("poiuy")).thenReturn(new String[]{"shiva1","tabh","iotiriojt"});
		Mockito.when(control.all_available_people("vpande")).thenReturn(new String[][]{{"poiuy","Shiva Pande"},{"shiva1","Vaibhav Nandra"},{"tabh","Vaibhav Shiva"}});
		Mockito.when(control.all_sent_requests("iotiriojt")).thenReturn(new String[][]{{"shiva1","Unfriended"},{"tabh","Not Responded"}});
		Mockito.when(control2.get_all_properties()).thenReturn(new String[]{"Blood Pressure","Pulse","Blood Pressure","group"});
		//Mockito.when(control3.)
	}
	
	@Test
	public void testAll_sent_requests() {
		String [][]res = new String[][]{{"shiva1","Unfriended"},{"tabh","Not Responded"}};
		assertEquals(control.all_sent_requests("iotiriojt")[1][1],"Not Responded");
		assertTrue(Arrays.deepEquals(control.all_sent_requests("iotiriojt"), res));
	}

	@Test
	public void testAll_available_people() {
		String [][]res = new String[][]{{"iotiriojt","Golf Singh"},{"tabh","vaibhav pande"},{"vpande","Vaibhav pande"}};
		
		assertEquals(control.all_available_people("vpande")[0][1],"Shiva Pande");
		assertFalse(Arrays.deepEquals(control.all_available_people("shiva1"), res));
		
	}

	@Test
	public void testGet_all_friends_list() {
		assertEquals(control.get_all_friends_list("shiva1")[1],("poiuy"));
		assertFalse(control.get_all_friends_list("vpande")[2].equals("poiuy"));
		assertTrue(control.get_all_friends_list("poiuy")[0].equals("shiva1"));
	}

	@Test
	public void testAll_recieved_requests(){
		String [][]res = new String[][]{{"iotiriojt","Golf Singh"},{"vpande","Vaibhav Pande"}};
		String [][]res2 = new String[][]{{"tabh","Ram Singh"},{"shiva1","Shiva Nandra"}};
		assertEquals(control.all_recieved_requests("tabh")[0][0],("iotiriojt"));
		assertTrue(Arrays.deepEquals(control.all_recieved_requests("tabh"), res));
		assertFalse(Arrays.deepEquals(control.all_recieved_requests("vpande"), res2));
		
	}
	
	@Test
	public void testGet_all_properties(){
		assertEquals(control2.get_all_properties()[2],("Blood Pressure"));
		assertFalse(control2.get_all_properties()[0].equals("group"));
		assertTrue(control2.get_all_properties()[1].equals("Pulse"));
		
		
	}
	
	@Test
	public void test(){
		
	}
}
