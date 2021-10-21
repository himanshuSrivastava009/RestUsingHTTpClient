package RestUsingHTTpClient.RestUsingHTTpClient;

public class TestSplit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String name="swati=kumari=himanshu=kumar=saurabh=priti";
//		String[] nm =name.split("=",4);
		
		String message="Hello World!";
		message=message.substring(6, 12)+message.substring(12, 6);
		System.out.println(message);
		
	}

}
