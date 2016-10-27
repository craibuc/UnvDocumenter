package com.cogniza;

import com.crystaldecisions.sdk.occa.infostore.IInfoObject;
import com.crystaldecisions.sdk.occa.infostore.IInfoObjects;

public class Program {

//	private static final String LOCAL_FOLDER = "c:\\users\\[username]\\desktop";

	public static void main(String[] args)
	{
		
		try {
			System.out.println("Connecting...");
			
			String token = Helper.getLogonToken("userName", "password", "server", "authentication");
			String query = "select SI_NAME, SI_ID from CI_INFOOBJECTS where SI_KIND = 'Webi' and SI_INSTANCE=0'";

			IInfoObjects infoObjects = Helper.getInfoObjects(token, query);
			for (Object object : infoObjects) {

				IInfoObject infoObject = (IInfoObject) object;
				System.out.println(infoObject.getTitle());
				
				String path = Helper.getInfoObjectPath(infoObject);
				System.out.println(path);
					
			}
			
//			String localPath = "null";

			// open from CMS
//			CmsResourceService remoteService = context.getService(CmsResourceService.class);  
//			String localPath = remoteService.retrieveUniverse("/Universes/universe_file.unx", LOCAL_FOLDER, true);  
			
			// open from disk
//			LocalResourceService localService = context.getService(LocalResourceService.class);
//            SlResource loadedResource = localService.load(localPath);
//            localService.retrieve(localPath, "test1.unx");
//			
//            RelationalBusinessLayer businessLayer = (RelationalBusinessLayer) loadedResource;
//            String localDFPath = businessLayer.getDataFoundationPath();
//            SlResource resource = localService.load(localDFPath); 
//
//			DataFoundation dataFoundation = null;
//			if (resource instanceof MonoSourceDataFoundation) {  
//				dataFoundation = (MonoSourceDataFoundation) resource;  
//			} 
//			else if (resource instanceof MultiSourceDataFoundation) {  
//				dataFoundation = (MultiSourceDataFoundation) resource;  
//			}
//
//			java.util.List<Table> tables = dataFoundation.getTables();			
//			for (Table table : tables) {
//				// name
//				System.out.println( String.format("Table: {0}", table.getName()) );
//				System.out.println( String.format("Description: {0}", table.getDescription()) );
//				System.out.println( String.format("Identifier: {0}", table.getIdentifier()) );
////				switch (table.getIdentifier()) {
////					case '':
////					case '':
////				}
//					
//			}
				
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} 

	} // main
	
}
