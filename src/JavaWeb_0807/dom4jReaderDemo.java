package JavaWeb_0807;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;
import java.util.List;


public class dom4jReaderDemo {
    public static void main(String[] args) throws DocumentException {
        try {
            //1.创建一个xml解析器对象
            SAXReader reader = new SAXReader();
            //2.读取xml文档，返回Document对象
            Document doc = reader.read(new File("./src/contact.xml"));

            System.out.println(doc);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Test
    public void tst1(){
        // 创建一个xml解析器
        SAXReader saxReader=new SAXReader();
        try {
            // 读取xml文档,返回document对象
            Document document=saxReader.read("./src/contact.xml");

            Iterator<Node> iterator=document.nodeIterator();
            while(iterator.hasNext()){
//                System.out.println(iterator.next());
                System.out.print("父节点:");
                Node node=iterator.next();
                String name=node.getName();
                System.out.println(name);

                // 继续取出来下面的节点
                //只有标签节点才有子节点
                // 先判断是不是标签节点
                if (node instanceof Element){
                    Element element=(Element)node;
                   Iterator<Node> eleIt= element.nodeIterator();
                   while (eleIt.hasNext()){
                       System.out.println("子节点 "+eleIt.next().getName());
                   }
                }
            }
//            System.out.println(document);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try {
            // 先获取xml解析器对象
            SAXReader saxReader=new SAXReader();
            // 然后读取xml文件, 返回Document对象
            Document document=saxReader.read(new File("./src/contact.xml"));
            // 得到 跟标签
            Element rootEle=document.getRootElement();

            getChildNode(rootEle);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取传入的标签下的所有子节点
     * @param ele 传入的节点
     */
    private void getChildNode(Element ele){
        // 输出当前节点的Name
        System.out.println(ele.getName());

        // 得到子节点
        Iterator<Node> iterator= ele.nodeIterator();
        while (iterator.hasNext()){
            Node node=iterator.next();

            // 如果还是标签
            if (node instanceof  Element){
                Element elem=(Element)node;
                // 递归
                getChildNode(elem);
            }
        }
    }

    @Test
    public void test3(){
        // 先创建xml解析器对象
        SAXReader saxReader=new SAXReader();
        Document document=null;
        // 读取xml,然后返回 document对象
        try {
            document=saxReader.read("./src/contact.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // 得到跟标签
        Element rootElement=document.getRootElement();
        // 得到标签的name
        String rootName=rootElement.getName();
        System.out.println("rootEleName: "+rootName);

        System.out.println("root下的第一个contact的标签: ");
        // 得到档期内标签下的指定名字的第一个子标签
        Element contactEle=rootElement.element("contact");
        System.out.println(contactEle.getName());

        System.out.println("所有的contact的集合: ");
//        System.out.println("List of contact: ");
        // 得到当前节点下的所有指定名字的标签
        Iterator<Element> contactIt=rootElement.elementIterator("contact");
        while(contactIt.hasNext()){
            System.out.println(contactIt.next().getName());
        }

        // 得到当前表现下的所有子标签
        List<Element> elementList=rootElement.elements();
        System.out.println("rootEle 下所有的标签: ");
        // 使用foreach 来遍历
//        for(Element ele :elementList){
//            System.out.println(ele.getName());
//        }

        // 使用迭代器遍历
        for (Element anElementList : elementList) {
            System.out.println(anElementList.getName());
        }

        // 如果想要获得更深层次的标签的话止能一层一层的获取
        Object data = document.getRootElement().element("contact").element("name").getData();
        System.out.println(data);
    }

    @Test
    public void test4(){
        // 先获取xml文件解析器对象
        SAXReader saxReader = new SAXReader();
        // 读取xml文件,返回Document对象
        Document document=null;
        try {
            document = saxReader.read("./src/contact.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 获取属性: 先获得属性所在的标签, 然后在获得属性
        Element contactEle = document.getRootElement().element("contact");
/*        String id = contactEle.attributeValue("id");
        System.out.println(id);*/

        /*// 先得到属性对象
        Attribute idAttr = contactEle.attribute("id");
        System.out.println(idAttr.getName()+" : "+idAttr.getValue());*/

        /*// 使用方法 attributes()
        List<Attribute> contactAttrList= contactEle.attributes();
        for (Attribute attr :contactAttrList){
//            System.out.println(attr);
            String attrName=attr.getName();
            String attrValue=attr.getValue();
            System.out.println(attrName+" : "+attrValue);
        }*/

        // 使用方法
        Iterator<Attribute> attributeIterator = contactEle.attributeIterator();
        while(attributeIterator.hasNext()){
            Attribute attr = attributeIterator.next();
            System.out.println(attr.getName()+" : "+attr.getValue());

        }

    }
    @Test
    public void test5(){
        // 先得到xml的解析器对象
        SAXReader saxReader=new SAXReader();
        // 读取xml文件,返回Document对象
        Document document=null;
        try {
            document=saxReader.read("./src/contact.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // 注意: 空格和换行也是文本内容


        // 获取文本 先获取标签, 然后获取标签上的文本(这里的文本就是标签体内的内容   )
        Element nametEle = document.getRootElement().element("contact").element("name");
        String nameTxt=nametEle.getText();
        System.out.println(nametEle.getName()+" : "+nameTxt);

        // 获取到指定标签的文本内容
        String emailEle = document.getRootElement().element("contact").elementText("email");
        System.out.println("email : "+emailEle);
    }


}
