package test;

import org.dom4j.Attribute;     
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.sun.corba.se.impl.orbutil.graph.Node;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class OmCreate {
	private Document xmlDoc;
	public String createOmStr(String[] csvdata) throws Exception  {  

		 
		 	// ����saxReader����  
	        SAXReader reader = new SAXReader();  
	        // ͨ��read������ȡһ���ļ� ת����Document����  
	        InputStream file=this.getClass().getResourceAsStream("/OM(Taxi).xml");
	        xmlDoc = reader.read(file);
	       //��ȡ���ڵ�Ԫ�ض���  
	        Element root = xmlDoc.getRootElement();
	        
	        
	        //sos:offering�ڵ�
	        //Element sos_offering = root.element("offering");
	        //sos_offering.setText("http://www.52north.org/test/offering/9");
	        
	        
	        //om:OM_Observation�ڵ�
	        Element om_OM_Observation=(Element)root.element("observation").element("OM_Observation");
	        
	        //om:phenomenonTime
	        Element om_phenomenonTime = om_OM_Observation.element("phenomenonTime").element("TimeInstant").element("timePosition");
	        om_phenomenonTime.setText(datetransform(csvdata[5]));
	        //om:resultTime
	        Element om_resultTime = om_OM_Observation.element("resultTime").element("TimeInstant").element("timePosition");
	        om_resultTime.setText(datetransform(csvdata[6]));
	         
	        //om:OM_Observation��Ԫ��om:result
	        Element om_result = om_OM_Observation.element("result");
	        Element datarecord = om_result.element("DataRecord");
	        List<Element> field = datarecord.elements("field");
	        field.get(0).element("Text").element("value").setText(csvdata[0]);
	        field.get(1).element("Text").element("value").setText(csvdata[1]);
	        field.get(2).element("Text").element("value").setText(csvdata[2]);
	        field.get(3).element("Text").element("value").setText(csvdata[3]);
	        field.get(4).element("Boolean").element("value").setText("false");
	        field.get(5).element("Count").element("value").setText(csvdata[7]);
	        field.get(6).element("Quantity").element("value").setText(csvdata[8]);
	        field.get(7).element("Quantity").element("value").setText(csvdata[9]);
	        field.get(8).element("Quantity").element("value").setText(csvdata[10]);
	        field.get(9).element("Quantity").element("value").setText(csvdata[11]);
	        field.get(10).element("Quantity").element("value").setText(csvdata[12]);
	        field.get(11).element("Quantity").element("value").setText(csvdata[13]);

	        return xmlDoc.asXML();
	}
	/*test
	public static void main(String[] args) throws Exception {
		CsvReader csvreader = new CsvReader();
		String[] strings = csvreader.readcsv();
		
		System.out.println(datetransform(strings[6]));
		OmCreate omCreate = new OmCreate();
		String string = omCreate.createOmStr(strings);
	
		System.out.println(string);
	}
     */
	 public void writer(Document document) throws Exception {  
	        // ���յĸ�ʽ  
	        // OutputFormat format = OutputFormat.createCompactFormat();  
	        // �Ű������ĸ�ʽ  
	        OutputFormat format = OutputFormat.createPrettyPrint();  
	        // ���ñ���  
	        format.setEncoding("UTF-8");  
	        // ����XMLWriter����,ָ����д���ļ��������ʽ  
	        // XMLWriter writer = new XMLWriter(new FileWriter(new  
	        // File("src//a.xml")),format);  
	        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
	                new FileOutputStream(new File("/new.xml")), "UTF-8"), format);  
	        // д��  
	        writer.write(document);  
	        // ����д��  
	        writer.flush();  
	        // �رղ���  
	        writer.close();  
	 }
	
	 private static String datetransform(String olddatestr) throws ParseException{
		
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		String datestr = df1.format(df2.parse(olddatestr));
		return datestr;
	 }
}
