package JavaWeb_0823;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class bufferedImage {
    @Test
    public void bufferedImg1() throws IOException {
        // 得到图片缓冲区
        BufferedImage bufferedImage=new BufferedImage(70,30,BufferedImage.TYPE_INT_RGB);
        // 得到绘制环境(像是一支笔)
        Graphics2D graphics2D= (Graphics2D) bufferedImage.getGraphics();

        graphics2D.setColor(Color.GREEN);// 笔现在的颜色
        // 填充整张图片(背景)
        graphics2D.fillRect(0,0,70,30);

        graphics2D.setFont(new Font("宋体",1,20));
        // 在这里要记得重新setColor()
        graphics2D.setColor(Color.RED);
        // 设置文字的左下角的位置
        graphics2D.drawString("joah",20,20);

        ImageIO.write(bufferedImage,"JPEG",new FileOutputStream("/home/joah/图片/java.jpg"));
    }
}
