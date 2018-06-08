package test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.*;

public class InsertObservation {
	   /**   
     * ����xml����server��   
     * @param url xml�������ݵ�ַ   
     * @param xmlString ���͵�xml������   
     * @return null
     * @throws DocumentException 
     * @throws IOException 
     * @throws HttpException 
     */      
	public static void sendPost(String url,String xmlString) throws  HttpException, IOException {    


        //����httpclient���߶���     
        HttpClient client = new HttpClient();      
        //����post���󷽷�     
        PostMethod myPost = new PostMethod(url);      
        //��������ʱʱ��     
        client.setConnectionTimeout(3000*1000);    
        //String responseString = null;      
           
            //��������ͷ������     
            myPost.setRequestHeader("Content-Type","application/xml");    
            myPost.setRequestHeader("charset","utf-8");    
            //���������壬��xml�ı����ݣ�һ����ֱ�ӻ�ȡxml�����ַ�����һ���Ƕ�ȡxml�ļ���������ʽ     
            myPost.setRequestBody(xmlString);     
            int statusCode = client.executeMethod(myPost);
          //ֻ������ɹ�200�ˣ��ŶԷ�������������  
            if(statusCode == HttpStatus.SC_OK){     
            String responseString = myPost.getResponseBodyAsString();
            System.out.println("response:");
            //System.out.print("\n");
            System.out.println(responseString);
            //myPost.releaseConnection();  
            }
            else if (statusCode != HttpStatus.SC_OK) {
				System.out.println("failed");
			} 
    }   
}
