package JavaWeb_0809;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.*;

public class dom4jWriteDemo {
    public static void main(String[] args) {

        // 读取一个xml文件
        Document document=null;
        // 指定写出文件的位置
        FileOutputStream fileOutputStream=null;
        // 创建写出对象
        XMLWriter xmlWriter=null;
        try {
            document= new SAXReader().read("./src/contact.xml");

            // 指定写出的格式
            OutputFormat format=null;
            // 紧凑的格式(没有空格和换行, 不好看)
//            format=OutputFormat.createCompactFormat();
            // 漂亮的格式(有换行和空格  看起来比较容易看)
            format=OutputFormat.createPrettyPrint();

            // 修改编码格式(文档的编码格式和文档内的xml申明都改变)
            format.setEncoding("UTF-8");
            //修改Document的对象
            // 吧修改后的对象写入文件

            fileOutputStream=new FileOutputStream("./src/JavaWeb_0809/contact2.xml");
//            fileOutputStream=new FileOutputStream("./src/contactDup.xml");

//            xmlWriter=new XMLWriter(fileOutputStream);
            xmlWriter=new XMLWriter(fileOutputStream,format);
            // 写出对象
            xmlWriter.write(document);
            // 关闭流
            xmlWriter.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 修改 xml 文件内容
     * 增加
     * 修改
     * 删除
     */
    @Test
    public void createXml(){


        // 指定文件的输出位置
        FileOutputStream fileOutputStream=null;
        // 创建写处对象
        XMLWriter xmlWriter=null;


        try {
            // 创建 xml 文档
            Document document = DocumentHelper.createDocument();
            // 增加一个标签
            Element rootEle=document.addElement("contactList");
            Element contactEle=rootEle.addElement("contact");
            Element nameEle=contactEle.addElement("name");
            // 添加属性
            contactEle.addAttribute("id","001");
            nameEle.addText("Summering");
            //
            // 修改Document 对象内容
            // 吧修改后的 document对象写入 xml 文档中

            fileOutputStream=new FileOutputStream("./src/JavaWeb_0809/contact3.xml");
            OutputFormat outputFormat=OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            // 在这里如果写了format 一定要记得加上
            xmlWriter=new XMLWriter(fileOutputStream,outputFormat);
            // 写出文件
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
    public void modifyXml(){
        // 读入一个 XML 文档
        Document document=null;
        // 指定输出文件的位置
        FileOutputStream fileOutputStream=null;
        // 创建写出对象
        XMLWriter xmlWriter=null;

        try {
            document=new SAXReader().read(new File("./src/contact.xml"));
            /*修改属性值*/
           /* // 得到标签对象, 然后得到属性对象, 在修改属性值
            Element contactEle = document.getRootElement().element("contact");
            Attribute idAttr = contactEle.attribute("id");
            idAttr.setValue("552976");*/

           /*// 得到标签对象, 然后add (因为同名属性只能存在一个所以可以修改)
            Element contactEle = document.getRootElement().element("contact");
            contactEle.addAttribute("id","555");*/

           /*修改文本*//*
            Element nameEle = document.getRootElement().element("contact").element("name");
            nameEle.setText("joahLi");*/

            /*删除: 标签,属性*/
            Element ageEle= document.getRootElement().element("contact").element("age");
//            ageEle.detach();
            // 找到要删除的标签, 然后在找到对应的父标签, 然后使用父标签的remove()方法来删除子标签
            ageEle.getParent().remove(ageEle);
            // 删除属性  得到属性对象,然后删除属性
            //得到第二个contact 标签
            Element conEle = (Element) document.getRootElement().elements().get(1);
            Attribute idAttr=conEle.attribute("id");
            idAttr.detach();



            fileOutputStream=new FileOutputStream("./src/JavaWeb_0809/contactModify.xml");
            // 创建 format对象
            OutputFormat outputFormat=OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("utf-8");

            // 每次一定要注意传入 format
            xmlWriter=new XMLWriter(fileOutputStream,outputFormat);
            xmlWriter.write(document);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
    }

}
