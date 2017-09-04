package JavaWeb_0811;

import JavaWeb_0807.Contact;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里写具体处理的过程
 */
public class myDefaultHandler extends DefaultHandler {
    // 存储所有联系人的对象d
    private List<Contact> list=new ArrayList<Contact>();
    // 保存一个联系人
    private Contact contact;
    // 存储当前读到的标签
    private String curTag;

    public List<Contact> getList() {
        return list;
    }

    // 在读到文档开始时调用
    @Override
    public void startDocument() throws SAXException {
        System.out.println("startDocument()");
        super.startDocument();

    }

    /**
     * 读到开始标签是调用
     * @param uri: 名称空间
     * @param localName: 本地名称
     * @param qName: 标签名称
     * @param attributes: 属性
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        /*System.out.println(" url : "+uri+" localName : "+localName+" qName : "+qName+" Attributes : "+attributes.  );
        super.startElement(uri, localName, qName, attributes);*/
        curTag=qName;
        // 读到 contact标签的时候就创建contact对象
        if("contact".equals(qName)){
             contact=new Contact();
            String id = attributes.getValue("id");
            contact.setId(id);

        }
    }

    /**
     * 读到文本时调用
     * @param ch:表示xml已经读到的全部文本
     * @param start:表示当前文本内容的开始位置
     * @param length: 当前文本内容的长度
     * @throws SAXException:
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        /*String content=new String(ch,start,length);
        System.out.println("characters()--->"+content);
        super.characters(ch, start, length);*/

        // 先拿到当前的文本内容
        String content=new String(ch,start,length);
//        System.out.println("\n content : "+content);

        // 在这里要注意equals() 左右的顺序,
        if("name".equals(curTag)){
//            System.out.println("name : "+content);
            contact.setName(content);
            /*System.out.println("setName success!");
            System.out.println(contact);*/
        }
        if("age".equals(curTag)){
            contact.setAge(content);
        }
        if ("qq".equals(curTag)){
            contact.setQq(content);
        }
        if ("email".equals(curTag)){
            contact.setEmail(content);
        }
        if("phone".equals(curTag)){
            contact.setPhone(content);
        }
    }

    //读到结束标签时调用
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        /*System.out.println("endElement()");
        super.endElement(uri, localName, qName);*/

        // 如果说在XML文件里面有无意义的换行的话加上这句不会在对应的属性位置输出换行空格之类的
        // 有无意义的换行空格, 换行空格会覆盖之前获取的具体的内容
        curTag=null;

        if ("contact".equals(qName)){
            list.add(contact);
        }
    }

    // 在读到文档结束是调用
    @Override
    public void endDocument() throws SAXException {
        System.out.println("endDocument()");
        super.endDocument();
    }
}

