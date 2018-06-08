package test;

import org.dom4j.Attribute;     
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.io.*;

public class SmlCreate {
	
	private static Document xmlDoc;
	
	public String createSmlString() throws DocumentException {
		
		SAXReader reader = new SAXReader();  
	    // 通过read方法读取一个文件 转换成Document对象  
	    InputStream file=this.getClass().getResourceAsStream("/insertsensor(Taxi).xml");
	    xmlDoc = reader.read(file);
	    String smlString = xmlDoc.asXML();
		return smlString;
	}
	
    
}
