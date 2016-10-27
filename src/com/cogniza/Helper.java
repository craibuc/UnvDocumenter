/**
 * 
 */
package com.cogniza;

import com.crystaldecisions.sdk.exception.SDKException;
import com.crystaldecisions.sdk.framework.CrystalEnterprise;
import com.crystaldecisions.sdk.framework.IEnterpriseSession;
import com.crystaldecisions.sdk.framework.ISessionMgr;
import com.crystaldecisions.sdk.occa.infostore.IInfoObject;
import com.crystaldecisions.sdk.occa.infostore.IInfoObjects;
import com.crystaldecisions.sdk.occa.infostore.IInfoStore;
import com.sap.sl.sdk.framework.SlContext;
import com.sap.sl.sdk.framework.cms.CmsSessionService;

/**
 * @author Craig Buchanan
 *
 */
public class Helper extends Program {

	/**
	 * 
	 * @param userName
	 * @param password
	 * @param server
	 * @param authentication
	 * @return String
	 */
	public static String getLogonToken(String userName, String password ,String server, String authentication)
	{
		IEnterpriseSession enterpriseSession = null;
		SlContext context = null;
		String token=null;
		
		try {
			System.out.println("Connecting...");
			 
			enterpriseSession = CrystalEnterprise.getSessionMgr().logon(userName, password, server, authentication);
			context = SlContext.create();
			context.getService(CmsSessionService.class).setSession(enterpriseSession);
			
			// token valid on any workstation; for 24 hours(1440 minutes); for unlimited number of logins (-1)
			token = enterpriseSession.getLogonTokenMgr().createLogonToken("", (24*60), -1);
			
		}
		catch (SDKException e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}

		return token;
		
	}

	public static IInfoObjects getInfoObjects(String token, String query)
	{

		ISessionMgr sessionMgr=null;
		IInfoObjects infoObjects=null;
		
		try {
			sessionMgr = CrystalEnterprise.getSessionMgr();

			IEnterpriseSession enterpriseSession = sessionMgr.getSession(token);
			
			IInfoStore infoStore = (IInfoStore) enterpriseSession.getService("InfoStore");
			
			infoObjects = (IInfoObjects) infoStore.query(query);

		} catch (SDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return infoObjects;
		
	}
	
	/**
	 * 
	 * @param infoObject
	 * @return String
	 */
	public static String getInfoObjectPath(IInfoObject infoObject)
	{
		String path = null;
		
		try {

			while (infoObject.getParentID() != 0) {
				infoObject = infoObject.getParent();
				path = "/" + infoObject.getTitle() + path;
			}

		}
		catch (SDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}

		return path;
	}

}
