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

		 
		 	// 创建saxReader对象  
	        SAXReader reader = new SAXReader();  
	        // 通过read方法读取一个文件 转换成Document对象  
	        InputStream file=this.getClass().getResourceAsStream("/OM(Taxi).xml");
	        xmlDoc = reader.read(file);
	       //获取根节点元素对象  
	        Element root = xmlDoc.getRootElement();
	        
	        
	        //sos:offering节点
	        //Element sos_offering = root.element("offering");
	        //sos_offering.setText("http://www.52north.org/test/offering/9");
	        
	        
	        //om:OM_Observation节点
	        Element om_OM_Observation=(Element)root.element("observation").element("OM_Observation");
	        
	        //om:phenomenonTime
	        Element om_phenomenonTime = om_OM_Observation.element("phenomenonTime").element("TimeInstant").element("timePosition");
	        om_phenomenonTime.setText(datetransform(csvdata[5]));
	        //om:resultTime
	        Element om_resultTime = om_OM_Observation.element("resultTime").element("TimeInstant").element("timePosition");
	        om_resultTime.setText(datetransform(csvdata[6]));
	         
	        //om:OM_Observation下元素om:result
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
	        // 紧凑的格式  
	        // OutputFormat format = OutputFormat.createCompactFormat();  
	        // 排版缩进的格式  
	        OutputFormat format = OutputFormat.createPrettyPrint();  
	        // 设置编码  
	        format.setEncoding("UTF-8");  
	        // 创建XMLWriter对象,指定了写出文件及编码格式  
	        // XMLWriter writer = new XMLWriter(new FileWriter(new  
	        // File("src//a.xml")),format);  
	        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
	                new FileOutputStream(new File("/new.xml")), "UTF-8"), format);  
	        // 写入  
	        writer.write(document);  
	        // 立即写入  
	        writer.flush();  
	        // 关闭操作  
	        writer.close();  
	 }
	
	 private static String datetransform(String olddatestr) throws ParseException{
		
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		String datestr = df1.format(df2.parse(olddatestr));
		return datestr;
	 }
}
