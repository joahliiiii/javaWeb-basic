package JavaWeb_0809;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class myDom4jWrite {

    @Test
    public void writeXml(){
        // 先创建 Document 对象,
        Document document= DocumentHelper.createDocument();
        // 设置输出路径
        FileOutputStream fileOutputStream=null;
        // 创建写出对象
        XMLWriter xmlWriter=null;
        try {
            // 修改文件内容
            Element rootEle = document.addElement("students");
            Element stuEle = rootEle.addElement("student");
            stuEle.addAttribute("id","001");
            Element nameEle = stuEle.addElement("name");
            // 在这里使用 addText 和 setText效果是一样的, 但是还是用addText吧......
            nameEle.addText("Summering");
            Element ageEle = stuEle.addElement("age");
            ageEle.addText("22");
            Element emailEle = stuEle.addElement("email");
            emailEle.addText("Summering@joah.com");
            Element addrEle = stuEle.addElement("address");
            addrEle.addText("joah pro");

            Element stu2Ele = rootEle.addElement("student");
            stu2Ele.addAttribute("id","002");
            Element name2Ele = stu2Ele.addElement("name");
            name2Ele.addText("joahLi");
            Element age2Ele = stu2Ele.addElement("age");
            age2Ele.addText("22");
            Element email2Ele = stu2Ele.addElement("email");
            email2Ele.addText("joah@joah.com");
            Element add2Ele = stu2Ele.addElement("address");
            add2Ele.setText("joah province");


            fileOutputStream=new FileOutputStream("./src/JavaWeb_0809/myContact.xml");
            OutputFormat outputFormat=OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("utf-8");
            xmlWriter=new XMLWriter(fileOutputStream,outputFormat);
            // 写出对象
            xmlWriter.write(document);
            // 关闭流
            xmlWriter.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void modifyXML(){
        // 先获取到Document对象
        Document document=null;
        // 设置输出路径
        FileOutputStream fileOutputStream=null;
        // 创建写入对象
        XMLWriter xmlWriter=null;

        try {
            // 输入输出同一个文件一开始我以为会出问题结果没出很开心
            document=new SAXReader().read("./src/JavaWeb_0809/myContact.xml");
            fileOutputStream=new FileOutputStream("./src/JavaWeb_0809/myContact.xml");
            // 修改document对象
            // 修改属性
            Element conEle = (Element) document.getRootElement().elements().get(1);
            conEle.attribute("id").setValue("552976");
            // 修改标签
            Element nameEle = conEle.element("name");
            nameEle.setText("joahWang");

            // 创建格式化对象
            OutputFormat outputFormat=OutputFormat.createPrettyPrint();
            // 格式化
            outputFormat.setEncoding("utf-8");
            xmlWriter = new XMLWriter(fileOutputStream,outputFormat);
            // 写入文件
            xmlWriter.write(document);
            // 关闭流
            xmlWriter.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void delEle(){
        // 先获取到 Document 对象
        Document document=null;
        // 设置输出文件路径
        FileOutputStream fileOutputStream=null;
        // 创建格式化对象
        OutputFormat outputFormat=null;
        // 创建写入 xml 的对象
        XMLWriter xmlWriter=null;


        try {
            document=new SAXReader().read("./src/JavaWeb_0809/myContact.xml");
            fileOutputStream=new FileOutputStream("./src/JavaWeb_0809/myContact.xml");
            // 格式化
//            outputFormat=OutputFormat.createCompactFormat();
            outputFormat=OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            xmlWriter=new XMLWriter(fileOutputStream,outputFormat);
            // 删除对应的 属性/Ele
            Element conEle = (Element) document.getRootElement().elements().get(1);
            conEle.detach();
            // 写入 xml
            xmlWriter.write(document);
            // 关闭 流
            xmlWriter.close();
            fileOutputStream.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
