package JavaWeb_0807;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class mydom4jReader {
    public static void main(String[] args) {
        // 完成的输出一xml文件
        // 先得到xml解释器对象
        SAXReader saxReader=new SAXReader();
        // 读取xml文件,返回Document对象
        Document document=null;
        try {
            document=saxReader.read("./src/contact.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElement = document.getRootElement();
        System.out.println(rootElement.getName());

        // 得到二级标签的List
        List<Element> elements = rootElement.elements();
        for (Element secEle : elements) {
            System.out.print("    "+secEle.getName());

            List<Attribute> secAttrs = secEle.attributes();
            for (Attribute secAttr : secAttrs) {
                System.out.print("  "+secAttr.getName()+" : "+secAttr.getValue());
            }
            System.out.println("\n");
            // 得到三级标签的集合
            List<Element> thirdEles = secEle.elements();
            for (Element thirdEle : thirdEles) {
                String thirdEleName = thirdEle.getName();
//                String thirdEleData = thirdEle.getText();
                Object thirdEleData = thirdEle.getData();
                System.out.println("        "+thirdEleName+" : "+thirdEleData);
            }
        }
    }

    @Test
    // 递归的输出xml文件,标签和文本
    public  void recursiveReadXml(){
        // 先得到xml解析器对象,
        SAXReader saxReader=new SAXReader();
        // 读取xml文件,返回Document对象
        Document document=null;
        try {
            document=saxReader.read("./src/contact.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 得到根节点
        Element rootElement = document.getRootElement();
        System.out.println(rootElement.getName());
        recRead(rootElement);

    }
    public void recRead(Element element){
        /*List<Attribute> attributes = element.attributes();
        for (Attribute attribute : attributes) {
            System.out.print(attribute.getName()+" : "+attribute.getValue());
        }
        System.out.println();*/

        List<Element> elements = element.elements();
        for (Element element1 : elements) {
            String eleName = element1.getName();
            System.out.print("  "+eleName);

            List<Attribute> eleAttrs = element1.attributes();

//            System.out.println(eleAttrs.size());

            for (Attribute eleAttr : eleAttrs) {
                System.out.print("  "+eleAttr.getName()+" : "+eleAttr.getValue());
            }
            // 在这里如果标签体内没有具体内容,使用getData()会返回 "" 的,getText()也一样
            Object data=element1.getData();
//            Object data=element1.getText();
            if (!data.equals("")){
//                System.out.println(data.equals("")+"---"+(data=="")+"--"+(data==null));
                System.out.print(" :"+data);
            }
            System.out.println();
            recRead(element1);
        }
    }
}
