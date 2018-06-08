package test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.*;

public class InsertObservation {
	   /**   
     * 发送xml请求到server端   
     * @param url xml请求数据地址   
     * @param xmlString 发送的xml数据流   
     * @return null
     * @throws DocumentException 
     * @throws IOException 
     * @throws HttpException 
     */      
	public static void sendPost(String url,String xmlString) throws  HttpException, IOException {    


        //创建httpclient工具对象     
        HttpClient client = new HttpClient();      
        //创建post请求方法     
        PostMethod myPost = new PostMethod(url);      
        //设置请求超时时间     
        client.setConnectionTimeout(3000*1000);    
        //String responseString = null;      
           
            //设置请求头部类型     
            myPost.setRequestHeader("Content-Type","application/xml");    
            myPost.setRequestHeader("charset","utf-8");    
            //设置请求体，即xml文本内容，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式     
            myPost.setRequestBody(xmlString);     
            int statusCode = client.executeMethod(myPost);
          //只有请求成功200了，才对返回内容做处理  
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
