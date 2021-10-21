package RestUsingHTTpClient.RestUsingHTTpClient;

import java.util.HashMap;

public class TestCaseParser {

	String test = "tc (|api id=TestCase 027::desc=Admin Add User Source::name=AdminUserSourceNew::type=json::verb=post|) (|val respcode=200::url=ldap://10.71.100.243:389::username=cn=administrator,cn=users,dc=child2,dc=child1,dc=filr2016,dc=com::type=ldap|)(|parm username=admin::password=novell::url=ldap://10.71.100.243:389::ldap_username=cn=administrator,cn=users,dc=child2,dc=child1,dc=filr2016,dc=com::ldap_password=8EiT_pa$$::username_attribute=mail::guid_attribute=GUID::user_dn=ou=TestOU,dc=child2,dc=child1,dc=Filr2016,dc=com::user_search=false|)";
	static String test1 = "tc(|api id=1::name=swati::age=90::description=test|)(|val value=10::value2=20|)(|param param=10::param1=20|)";
	static HashMap<String, String> values = new HashMap<String, String>();
	static HashMap<String, String> ApiDescription = new HashMap<String, String>();
	static HashMap<String, String> ApiValues = new HashMap<String, String>();
	static HashMap<String, String> ApiParameters = new HashMap<String, String>();
	static HashMap<String, String> abf = new HashMap<String, String>();

	public static void splitString() {
		if (test1.startsWith("tc")) {
			String x = test1.split("[|]")[1];
			System.out.println(x);
			String[] abc = x.split("::");
			for (int i = 0; i < abc.length; i++) {
				System.out.println(abc[i]);
				values.put(abc[i].split("=")[0], abc[i].split("=")[1]);
			}
			System.out.println(values);
		}
	}

	public static void splitAgain() {
		//	"tc(|api=1::name=swati::age=90|)(|value=10::value2=20|)(|param=10::param2=20|)";

		String description=test1.split("[(|]")[8];
	//	System.out.println(description);
		String abc[] =description.split("::");
		for(int i=0;i<abc.length;i++)
		{
			String ah=abc[i].split("=")[0];
			String ahd=abc[i].split("=")[1];
		}
		System.out.println(ApiDescription);
		System.out.println(ApiDescription.get("param1"));
		
		
		
			

	}

	public static void splitFinal()
	{
		//	"tc(|api=1::name=swati::age=90|)(|value=10::value2=20|)(|param=10::param2=20|)";

		String description=test1.split("[(|]")[8];
	//	System.out.println(description);
		String abc[] =description.split("::");
		for(int i=0;i<abc.length;i++)
		{
			String ah=abc[i].split("=")[0];
			String ahd=abc[i].split("=")[1];
			if(i==0) {
				String h=ah.split(" ")[1];
				ApiDescription.put(h, ahd);
			}
			else {
			ApiDescription.put(ah, ahd);
			}
		//	ApiDescription.put(ah, ahd);
		}
		System.out.println(ApiDescription);
		System.out.println(ApiDescription.get("param1"));
		
	}
	public static void main(String[] args) {
		//splitString();
		//splitAgain();
		//splitFinal();
		abc();
	}
	public static void abc() {
		int[] ar1= {1,2,3,4,5,6};
		int total;
		
		for(int i=0;i<ar1.length;i++)
		{
			for(int j=1;j<(ar1.length);j++) {
				if(ar1[i]==ar1[j]) {
					continue;
				}
				else {
					total=ar1[i]+ar1[j];
					if(total==7)
					{
						System.out.println(ar1[i]+"and"+ar1[j]);	
					}
				}
			}
		}
		
	}
}
