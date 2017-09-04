package JavaWeb_0807;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class readerDemo {
    public static void main(String[] args) {
        ArrayList<Contact> contactList = new ArrayList<>();

        // 读取xml 封装对象
        SAXReader saxReader=new SAXReader();
        String filePath="./src/contact.xml";
        File xmlFile=new File(filePath);
        Document document=null;
        try {
            document=saxReader.read(xmlFile);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 读取contact
        Iterator<Element> contactIterator = document.getRootElement().elementIterator("contact");
        while(contactIterator.hasNext()){
            Element element=contactIterator.next();
            Contact contact=new Contact();
            contact.setId(element.attributeValue("id"));
            contact.setName(element.elementText("name"));
            contact.setAge(element.elementText("age"));
            contact.setPhone(element.elementText("phone"));
            contact.setEmail(element.elementText("email"));
            contact.setQq(element.elementText("qq"));
            contactList.add(contact);
        }
        for (Contact contact : contactList) {
            System.out.println(contact);
        }

    }
}
