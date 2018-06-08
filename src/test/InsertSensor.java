package test;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.DocumentException;

import com.sun.glass.ui.Application;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.HttpException;

public class InsertSensor {
	
	public void sendPost(String url,String xmlstring) throws HttpException, IOException {
		HttpClient httpClient = new HttpClient();
		PostMethod myPost = new PostMethod(url);
		httpClient.setConnectionTimeout(3000*1000);
        myPost.setRequestHeader("Content-Type","application/xml");    
        myPost.setRequestHeader("charset","utf-8");
		myPost.setRequestBody(xmlstring);
		int statusCode = httpClient.executeMethod(myPost);
		
		if (statusCode == HttpStatus.SC_OK) {
			System.out.println("InsertSensor Success");
		}
		else{
			System.out.println("InsertSensor fail");
		}
		
	}
	/*test
	 * public static void main(String[] args) throws DocumentException{
	 
		
		SmlCreate smlCreate = new SmlCreate();
		String string = smlCreate.createSmlString();
		InsertSensor insertSensor = new InsertSensor();
		try {
			insertSensor.sendPost("http://localhost:8080/52n-sos-webapp/service", string);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
}
