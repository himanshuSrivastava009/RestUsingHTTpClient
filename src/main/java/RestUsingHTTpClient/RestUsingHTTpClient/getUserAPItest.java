package RestUsingHTTpClient.RestUsingHTTpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.jayway.jsonpath.JsonPath;

public class getUserAPItest {
	static CloseableHttpResponse response = null;
	static CloseableHttpClient httpclient = null;
	static HttpEntity entity = null;
	static StringEntity requestEntity=null;

	public static void getCall() {
		// 1. Creatign own Rest Client
		httpclient = HttpClientBuilder.create().build();

		// 2,Create a get request with URL

		HttpGet getRequsest = new HttpGet("https://blr8-110-113.apac.novell.com/rest/self");

		// 3.Add headers
		getRequsest.addHeader("Accept", "*/*");
		getRequsest.addHeader("content-type", "aplication/json");
		getRequsest.addHeader("Authorization", "Basic aGltYW5zaHU6bm92ZWxsQDEyMw==");
		try {

			// 4. Execute the API and get the Response
			response = httpclient.execute(getRequsest);
			System.out.println("status code=====" + response.getStatusLine());
			entity = response.getEntity();
			System.out.println(entity);
			String body = EntityUtils.toString(entity);
			System.out.println("Response Body=========\n" + body);

		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void postCall() {
		String url = "https://blr8-110-113.apac.novell.com/rest/admin/user_sources";
		String jsonBody = "{\r\n"
				+ "	\"url\": \"ldap://10.71.100.243:389\",\r\n"
				+ "	\"username_attribute\": \"cn=administrator,cn=users,dc=child2,dc=child1,dc=filr2016,dc=com\",\r\n"
				+ "	\"guid_attribute\": \"ObjectGUID\",\r\n"
				+ "	\"username\": \"cn=administrator,cn=users,dc=child2,dc=child1,dc=filr2016,dc=com\",\r\n"
				+ "	\"password\": \"8EiT_pa$$\",\r\n"
				+ "	\"user_contexts\": [{\r\n"
				+ "		\"base_dn\": \"OU=LDAPTestSyncOU,DC=child2,DC=child1,DC=Filr2016,DC=com\",\r\n"
				+ "		\"home_dir_config\": {\r\n"
				+ "			\"type\": \"home_dir_attribute\"\r\n"
				+ "		}\r\n"
				+ "	}],\r\n"
				+ "	\"group_contexts\": [{\r\n"
				+ "		\"base_dn\": \"\"\r\n"
				+ "	}]\r\n"
				+ "}";

		httpclient = HttpClientBuilder.create().build();

		HttpPost postRequest = new HttpPost(url);
		postRequest.addHeader("content-type", "application/json");
		postRequest.addHeader("Accept","*/*");
		postRequest.addHeader("Authorization", "Basic YWRtaW46bm92ZWxs");
		try {
			requestEntity = new StringEntity(jsonBody,"application/json","UTF-8");
			postRequest.setEntity(requestEntity);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		try {
			response=httpclient.execute(postRequest);
		
			HttpEntity entity=response.getEntity();
			String responseBody=EntityUtils.toString(entity);
			System.out.println(responseBody);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
	}
	public static void CustomHttpClient()
	{
		String host="blr8-110-113.apac.novell.com";
		HttpHost url = new HttpHost(host);
		String application="https://blr8-110-113.apac.novell.com/rest/self";
		
		HttpGet get = new HttpGet(application);
		
	
		UsernamePasswordCredentials credentails = new UsernamePasswordCredentials("admin", "novell");
		
		AuthScope authscope = new AuthScope(url);
		CredentialsProvider creds = new BasicCredentialsProvider();
		//creds.setCredentials(authscope, credentails);
		creds.setCredentials(authscope, credentails);
		HttpClientBuilder clientBuilder = HttpClients.custom();
		clientBuilder=clientBuilder.setDefaultCredentialsProvider(creds);
		httpclient=clientBuilder.build();
		
		try {
			response=httpclient.execute(get);
			entity=response.getEntity();
			String body=EntityUtils.toString(entity);
			System.out.println(body);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public  static void listNetFolderServer()
	{
		CloseableHttpResponse resp=null;
		String url="blr8-100-178.apac.novell.com";
		String endPoints="/rest/admin/net_folder_servers";
		String restURL="https://"+url+endPoints;
		System.out.println(restURL);
		//1.Create a HTTP host
		HttpHost host = new HttpHost(url);
		//2.Create get method
		HttpGet getRequest = new HttpGet(restURL);
		//3.Add header
		getRequest.addHeader("content-type","*/*");
		getRequest.addHeader("Accept","application/json");
		getRequest.addHeader("Authorization","Basic YWRtaW46bm92ZWxs");
		
	/*	
		//4.Create a AuthScope
		
		AuthScope authScope= new AuthScope(host);
		
		//5.Create Credentials provider
		CredentialsProvider credentialsProvider= new BasicCredentialsProvider();
		UsernamePasswordCredentials creds = new UsernamePasswordCredentials("admin", "novell");
		
		//6.Set the credentials
			credentialsProvider.setCredentials(authScope,creds );
		//7.Create Http Custom Client
		
		HttpClientBuilder builder=HttpClients.custom();
		
		//8. Build the client by setting credentials 
		builder=builder.setDefaultCredentialsProvider(credentialsProvider);
		//9.Build the Httpclient
		CloseableHttpClient httpclient=builder.build();
		//10. Execute the httpClient
		try {
			resp=httpclient.execute(getRequest);
			System.out.println("Rawresponse is ======="+resp);
			HttpEntity entiy=resp.getEntity();
			System.out.println("entity is "+entity);
			String respbody=EntityUtils.toString(entiy);
			System.out.println("List of NF Servers \n"+respbody);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
	httpclient=	HttpClientBuilder.create().build();
	String jsonPath="$.items[*].name";
	try {
		response=httpclient.execute(getRequest);
		System.out.println(response);
		System.out.println(response.getEntity());
		HttpEntity entity=response.getEntity();
		System.out.println("status Line===="+response.getStatusLine());
		System.out.println(response.getAllHeaders());
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>Value is>>>>>>>"+EntityUtils.toString(entity));
		String rbody=EntityUtils.toString(entity);
		System.out.print("body is "+rbody);
		List<Object> obj=JsonPath.read(rbody,jsonPath);
		System.out.println(obj);
		System.out.println(obj.contains("10.71.100.246-hk"));
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
		
	}
	
	public static void CreateLDAPUser()
	{
		String url="";
		String jsonBody="";
		
		HttpPut put = new HttpPut();
	}
	
	public static void CreateNewLDAPUser()
	{
		String url="blr8-100-178.apac.novell.com";
		String restEndPoints="/rest/admin/user_sources";
		String hostUrl="https://"+url+restEndPoints;
		System.out.println(hostUrl);
		String jsonBody="{\r\n"
				+ "	\"url\": \"ldap://10.71.100.243:389\",\r\n"
				+ "	\"username_attribute\": \"sAMAccountName\",\r\n"
				+ "	\"guid_attribute\": \"ObjectGUID\",\r\n"
				+ "	\"username\": \"cn=administrator,cn=users,dc=child2,dc=child1,dc=filr2016,dc=com\",\r\n"
				+ "	\"password\": \"8EiT_pa$$\",\r\n"
				+ "	\"user_contexts\": [{\r\n"
				+ "		\"base_dn\": \"OU=LDAPTestSyncOU,DC=child2,DC=child1,DC=Filr2016,DC=com\",\r\n"
				+ "		\"home_dir_config\": {\r\n"
				+ "			\"type\": \"home_dir_attribute\"\r\n"
				+ "		}\r\n"
				+ "	}],\r\n"
				+ "	\"group_contexts\": [{\r\n"
				+ "		\"base_dn\": \"\"\r\n"
				+ "	}]\r\n"
				+ "}";
		HttpPost postCall = new HttpPost(hostUrl);
		postCall.addHeader("accept","*/*");
		postCall.addHeader("content-type","application/json");
		HttpHost host = new HttpHost(url);
		
		ContentType ctype=ContentType.create("UTF-8");
		
		requestEntity = new StringEntity(jsonBody, ctype);
		postCall.setEntity(requestEntity);
		
		UsernamePasswordCredentials usernamepassword = new UsernamePasswordCredentials("admin","novell");
		CredentialsProvider creds = new BasicCredentialsProvider();
		creds.setCredentials(new AuthScope(host), usernamepassword);
		System.out.println("credentials are====== "+creds.toString());
		HttpClientBuilder clientBuilder=HttpClients.custom();
		System.out.println("postcall value ======"+postCall.toString());
		clientBuilder=clientBuilder.setDefaultCredentialsProvider(creds);
		httpclient=clientBuilder.build();
		System.out.println("httpclient value======"+httpclient);
		try {
			response=httpclient.execute(postCall);
			System.out.println("response is ======="+response);
			 entity=response.getEntity();
			String responseBody=EntityUtils.toString(entity);
			System.out.println(responseBody);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void UpdateLDAPUserDetails()
	{

		String url="blr8-100-178.apac.novell.com";
		String restEndPoints="/rest/admin/user_sources/sync_config";
		String hostUrl="https://"+url+restEndPoints;
		System.out.println(hostUrl);
	}
	
	public static void CheckNFServer()
	{
		String url="blr8-110-113.apac.novell.com";
		String restEndPoints="/rest/admin/net_folder_servers";
		String hostUrl="https://"+url+restEndPoints;
		System.out.println(hostUrl);

		HttpGet getCall = new HttpGet(hostUrl);
		getCall.addHeader("accept","*/*");
		getCall.addHeader("content-type","application/json");
		HttpHost host = new HttpHost(url);
		AuthScope scope = new AuthScope(host);
		
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin","novell");
		BasicCredentialsProvider creds = new BasicCredentialsProvider();
		creds.setCredentials(scope, credentials);
		
		HttpClientBuilder clientBuilder=HttpClients.custom();
		clientBuilder=clientBuilder.setDefaultCredentialsProvider(creds);
		CloseableHttpClient httpClient=clientBuilder.build();
		String	responseBody=null;
		String jsonPath="$.items[*].name";
		try {
			response=httpClient.execute(getCall);
			System.out.println("response======="+response);
			HttpEntity	Entity=response.getEntity();
			responseBody=EntityUtils.toString(Entity);
			System.out.println("body is ===="+responseBody);
			List<Object> name=JsonPath.read(responseBody, jsonPath);
			System.out.println("name======"+name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static void updateNFServer()
	{

		String url="blr8-110-113.apac.novell.com";
		String restEndPoints="/rest/admin/user_sources";
		String hostUrl="https://"+url+restEndPoints;
		System.out.println(hostUrl);
		
		//1. Create Host
		HttpHost host = new HttpHost(url);
		
		//2. Create Headers
		HttpPost post = new HttpPost(hostUrl);
	//	Method[] method=	post.getClass().getMethods();
	//	System.out.println(post.getURI());
//		for(Method method1:method)
//		{
//			System.out.println(method1);
//		}
		post.addHeader("content-type", "application/json");
		post.addHeader("accept", "*/*");
		
		//4. JSON Body
		
		String jsonBody="{\r\n"
				+ "	\"url\": \"ldap://10.71.100.243:389\",\r\n"
				+ "	\"username_attribute\": \"sAMAccountName\",\r\n"
				+ "	\"guid_attribute\": \"ObjectGUID\",\r\n"
				+ "	\"username\": \"cn=administrator,cn=users,dc=child2,dc=child1,dc=filr2016,dc=com\",\r\n"
				+ "	\"password\": \"8EiT_pa$$\",\r\n"
				+ "	\"user_contexts\": [{\r\n"
				+ "		\"base_dn\": \"OU=LDAPTestSyncOU,DC=child2,DC=child1,DC=Filr2016,DC=com\",\r\n"
				+ "		\"home_dir_config\": {\r\n"
				+ "			\"type\": \"home_dir_attribute\"\r\n"
				+ "		}\r\n"
				+ "	}],\r\n"
				+ "	\"group_contexts\": [{\r\n"
				+ "		\"base_dn\": \"\"\r\n"
				+ "	}]\r\n"
				+ "}";
		
		
		try {
			HttpEntity entity = new StringEntity(jsonBody);
			post.setEntity(entity);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		//Get the headers
		
	System.out.println("==============="+post.getEntity());
		//3. Create Auth Scope
		AuthScope scope = new AuthScope(host);
		UsernamePasswordCredentials usernamepassword = new UsernamePasswordCredentials("admin", "novell");
		
		CredentialsProvider creds = new BasicCredentialsProvider();
		creds.setCredentials(scope, usernamepassword);
		
		//4. Build a HttpClient
		HttpClientBuilder clientBuilder=HttpClients.custom();
		clientBuilder=clientBuilder.setDefaultCredentialsProvider(creds);
		CloseableHttpClient httpclient=clientBuilder.build();
		
		//5.Execute the http Client
		try {
			System.out.println(httpclient.execute(post));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void updateUserNFServer()
	{
		String url="automation.apac.novell.com";
		String restEndPoints="/rest/admin/user_sources";
		String hostUrl="https://"+url+restEndPoints;
		System.out.println(hostUrl);
		
		HttpHost host = new HttpHost(url);
		
		HttpPut putcall = new HttpPut(hostUrl);
		putcall.addHeader("accept","application/json");
		putcall.addHeader("content-type","application/json");
		
		String jsonBody="{\r\n"
				+ "	\"id\": 1,\r\n"
				+ "	\"name\": \"Tanmay-Auto-110-31\",\r\n"
				+ "	\"driver_type\": \"oes\",\r\n"
				+ "	\"only_sync_dirs\": false,\r\n"
				+ "	\"server_path\": \"\\\\\\\\blr8-111-101.labs.blr.novell.com\\\\MADHU_VOL\",\r\n"
				+ "	\"proxy_dn\": \"cn=admin,o=novell\",\r\n"
				+ "	\"proxy_use_identity\": false,\r\n"
				+ "	 \"proxy_password\" : \"novell\",\r\n"
				+ "	\"auth_type\": \"nmas\",\r\n"
				+ "	\"index_content\": false,\r\n"
				+ "	\"jits_enabled\": true,\r\n"
				+ "	\"jits_max_age\": 60000,\r\n"
				+ "	\"jits_max_acl_age\": 3600000,\r\n"
				+ "	\"allow_client_initiated_sync\": true\r\n"
				+ "}";
		
		AuthScope scope = new AuthScope(host);
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin", "novell");
		
		BasicCredentialsProvider creds = new BasicCredentialsProvider();
		creds.setCredentials(scope, credentials);
		StringEntity entity=null;
		try {
			entity = new StringEntity(jsonBody);
			putcall.setEntity(entity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpClientBuilder clientBuilder=HttpClients.custom();
		clientBuilder=clientBuilder.setDefaultCredentialsProvider(creds);
		CloseableHttpClient httpclient=clientBuilder.build();
		
		try {
			System.out.println(httpclient.execute(putcall));
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {

		//getCall();
		//postCall();
		//CustomHttpClient();
		//listNetFolderServer();
		//CreateNewLDAPUser();
		//UpdateLDAPUserDetails();
		//CheckNFServer();
		//updateNFServer();
		updateUserNFServer();
	}
}
