package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.locks.Condition;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.dom4j.DocumentException;

import sun.security.krb5.internal.crypto.crc32;


public class Taxi extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String[] csvdata;//
	private static CsvReader cr =new CsvReader();
 	
	private static int mf;
	private static int mc;//TODO
	private static String mp;//TODO
	private static String rf;
	private static int count = 0;
	private static int n = 0;

	//´¦ÀípostÇëÇó

	protected void doPost(HttpServletRequest req,HttpServletResponse resp) {
		int measurementFrequency = Integer.parseInt(req.getParameter("measurementFrequency"));
		int measurementCount = Integer.parseInt(req.getParameter("measurementCount"));
		String measurementPurpose = req.getParameter("measurementPurpose");
		String reference = req.getParameter("reference");
		
		mf = measurementFrequency;
		mc = measurementCount;
		rf = reference;

		if (measurementCount > (cr.size-n) || measurementFrequency > 10 || reference == null || measurementCount ==0 || measurementFrequency == 0) {
			resp.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
		else {
			if (count == 0) {
				try{
					resp.setStatus(HttpStatus.SC_OK);
					//insertsensor
					SmlCreate sc = new SmlCreate();
					String sml = sc.createSmlString();
					InsertSensor is = new InsertSensor();
					is.sendPost(reference, sml);
					//Task
					Timer timer = new Timer();  
					MyTask myTask = new MyTask();
					timer.schedule(myTask, 1000, measurementFrequency * 1000);
				}catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			} 
			else{
				resp.setStatus(HttpStatus.SC_NOT_ACCEPTABLE);
			}
		}
	}

/*
	public static void main(String[] args) throws Exception {    
		
		int measurementFrequency = 2;//s
		int measurementCount = 5;
		String reference = "http://localhost:8080/52n-sos-webapp/service";
		mc = measurementCount;
		mf = measurementFrequency;
		rf = reference;
		try {
			SmlCreate sc = new SmlCreate();
			String sml = sc.createSmlString();
			InsertSensor is = new InsertSensor();
			is.sendPost(reference, sml);
			//Task
			Timer timer = new Timer();  
			MyTask myTask = new MyTask();
			timer.schedule(myTask, 1000, measurementFrequency * 1000);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
*/
      
    public static class MyTask extends java.util.TimerTask{      
        public void run(){
        	csvdata = cr.readcsv();
        	OmCreate omCreate = new OmCreate();
        	if (csvdata == null || count >= mc) {
	        	System.gc();
	 			cancel();
	 			System.out.println("stop");
	 			count = 0;
	        	}
        	else {
				try {
					String omXml = omCreate.createOmStr(csvdata);
					InsertObservation.sendPost(rf,omXml);
					count++;
					n++;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	}
        	
        }
    }
	}
