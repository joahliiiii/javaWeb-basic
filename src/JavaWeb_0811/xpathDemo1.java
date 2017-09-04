package JavaWeb_0811;//package JavaWeb_0810;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Scanner;

public class xpathDemo1 {

    public static void main(String[] args) {
        /**
         * 试着用 xml 和 xpath 模仿一个登录的过程
         */
        Scanner in = new Scanner(System.in);
        System.out.print("please input username: ");
        String username = in.nextLine();
        System.out.print("please input password: ");
        String password = in.nextLine();
        Document document = null;
        try {
            document = new SAXReader().read(new File("./src/JavaWeb_0811/userInfo.xml"));
            String exp = "//user[@username='" + username + "' and @password='" + password + "']";
            Node node = document.selectSingleNode(exp);
            if (node != null) {
                System.out.println("login suceccful!");
            } else {
                System.out.println("login failed!");
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        }
        in.close();
    }



}
