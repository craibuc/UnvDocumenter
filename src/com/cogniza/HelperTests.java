package com.cogniza;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

import com.crystaldecisions.sdk.occa.infostore.IInfoObject;
import com.crystaldecisions.sdk.occa.infostore.IInfoObjects;

public class HelperTests {

	String token=null;
	
	@BeforeClass
	public void atBeginning() {

		// TODO find a better way to capture input
		String userName = getPrompt("UserName");
		String password = getPrompt("Password");
		String server = getPrompt("server");
		String authentication = "secWinAD";

		token = Helper.getLogonToken(userName,password,server,authentication);
	}
	
	@Test
	public void testGetLogonToken() {
		
		// arrange
		String actual=null;
		
		// act (delegated to atBeginning)
		actual = token;
		
		// assert
		assertTrue(!actual.equals(null));
	}

	@Test
	public void testGetInfoObjects() {
		
		// arrange
		String token=null;
		String query = "SELECT si_id, si_name, si_kind FROM ci_infoobjects WHERE si_kind='Folder'";
		IInfoObjects infoObjects = null;
					
		// act
		infoObjects = Helper.getInfoObjects(token, query);
						
		// assert
		assertTrue(!infoObjects.equals(null));

	}

	@Test
	public void testGetInfoObjectPath()  {
		
		// arrange
		String actual=null;
		// get 'Root Folder' folder
		String query = "SELECT si_id, si_name, si_path FROM ci_infoobjects WHERE si_id=23";
		
		// act
		IInfoObject infoObject = (IInfoObject) Helper.getInfoObjects(token, query).get(0);
		actual = Helper.getInfoObjectPath(infoObject);
		
		// assert
		assertTrue(!actual.equals(null));

	}

	public static String getPrompt(String prompt) {
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println(prompt);
					    
		    String text = scanner.nextLine();
		    return text;
		}
		finally {
			scanner.close();
		}
	}
	
}
