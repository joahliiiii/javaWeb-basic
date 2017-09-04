package JavaWeb_0811;

import JavaWeb_0807.Contact;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JAXDemo {
    /**
     * DOM 是直接全部加载
     * SAX 是一点一点加载
     */
    @Test
    public void testJax(){
        try {
            // 创建SAXParser的对象
            SAXParser saxParser= SAXParserFactory.newInstance().newSAXParser();
            // 调用 parse 方法
            saxParser.parse(new File("./src/contact.xml"), new myDefaultHandler());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJax2(){
        // 在这里一定要注意读取的 XML 文件要是规格化的不是容易看的

        try {
            // 创建SAXPraser 的对象
            SAXParser saxParser=SAXParserFactory.newInstance().newSAXParser();
            // 先创建Handler 对象
            myDefaultHandler handler = new myDefaultHandler();
            // 调用 parse 方法
            saxParser.parse(new File("./src/contactCom.xml"),handler);
            List<Contact> contactList = handler.getList();
            for (Contact contact : contactList) {
                System.out.println(contact);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
