package RestUsingHTTpClient.RestUsingHTTpClient;

import java.util.HashMap;

public class TestCaseParserNew {

	static String test = "tc (|api id=TestCase 027::desc=Admin Add User Source::name=AdminUserSourceNew::type=json::verb=post|) (|val respcode=200::url=ldap://10.71.100.243:389::username=cn=administrator,cn=users,dc=child2,dc=child1,dc=filr2016,dc=com::type=ldap|)(|parm username=admin::password=novell::url=ldap://10.71.100.243:389::ldap_username=cn=administrator,cn=users,dc=child2,dc=child1,dc=filr2016,dc=com::ldap_password=8EiT_pa$$::username_attribute=mail::guid_attribute=GUID::user_dn=ou=TestOU,dc=child2,dc=child1,dc=Filr2016,dc=com::user_search=false|)";

	static HashMap<String, String> Parmmap = new HashMap<String, String>();
	static HashMap<String, String> Valuemap = new HashMap<String, String>();
	static HashMap<String, String> Apimap = new HashMap<String, String>();
	static String api = null;
	static String[] mapi = null;

	public static HashMap<String, String> getTestCaseAPI() {
		String[] splitValues = test.split("[(|]");
		for (int i = 0; i < splitValues.length; i++) {
			// System.out.println(splitValues[i]);
			
				// System.out.println(splitValues[i]);
				if (splitValues[i].contains("api")) {
					api = splitValues[i].split("api")[1];
					// System.out.println("=======" + api);
					mapi = api.split("::");
					for (int j = 0; j < mapi.length; j++) {
						Apimap.put(mapi[j].split("=")[0].trim(), mapi[j].split("=")[1].trim());
					}

				}
			
		}
		return Apimap;
	}

	public static HashMap<String, String> getTestCaseVAL() {
		String[] splitValues = test.split("[(|]");
		for (int i = 0; i < splitValues.length; i++) {
			// System.out.println(splitValues[i]);
			
				// System.out.println(splitValues[i]);
				if (splitValues[i].contains("val")) {
					api = splitValues[i].split("val")[1];
					// System.out.println("=======" + api);
					mapi = api.split("::");
					for (int j = 0; j < mapi.length; j++) {
						Valuemap.put(mapi[j].split("=",2)[0].trim(), mapi[j].split("=",2)[1].trim());
					}

				}
			
		}
		return Valuemap;
	}
	public static HashMap<String, String> getTestCasePARM() {
		String[] splitValues = test.split("[(|]");
		for (int i = 0; i < splitValues.length; i++) {
			// System.out.println(splitValues[i]);
			
				// System.out.println(splitValues[i]);
				if (splitValues[i].contains("parm")) {
					api = splitValues[i].split("parm")[1];
					// System.out.println("=======" + api);
					mapi = api.split("::");
					for (int j = 0; j < mapi.length; j++) {
						Parmmap.put(mapi[j].split("=",2)[0].trim(), mapi[j].split("=",2)[1].trim());
					}

				}
			
		}
		return Parmmap;
	}

	public static void main(String[] args) {
	
		HashMap<String, String> api = getTestCaseAPI();
		HashMap<String, String> val = getTestCaseVAL();
		HashMap<String, String> param = getTestCasePARM();
		
		System.out.println(api);
		System.out.println(val);
		System.out.println(param);


	}

}
