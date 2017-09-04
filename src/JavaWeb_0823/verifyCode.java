package JavaWeb_0823;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class verifyCode {
    private int imgWidth=100;
    private int imgHeigth=50;
    private Random rdm=new Random();
    private String codes  = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";

    @Test
    public void generateCode() throws IOException {
        //
        BufferedImage bufferedImage=new BufferedImage(imgWidth,imgHeigth,BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D= (Graphics2D) bufferedImage.getGraphics();
        // 设置背景颜色
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0,0,imgWidth-1,imgHeigth-1);

        // 划干扰线
        getLine(bufferedImage);
        // 设置噪点
        getYawpPoint(bufferedImage);

        // 设置字体
        graphics2D.setFont(new Font("Algerian",Font.BOLD,35));
        // 设置字的颜色
        graphics2D.setColor(Color.WHITE);

        // 把文字写到图片
        graphics2D.drawString(getTxt(),20,30);
        // 扭曲图片, 但是效果不好
        /*for(int i=0;i<4;i++){
            String str = ""+rdm.nextInt(10);

            //处理旋转
            AffineTransform Tx = new AffineTransform();
            Tx.rotate(Math.random(), 5+i*15, imgHeigth-5);
            //用弧度测量的旋转角度,旋转锚点的 X 坐标,旋转锚点的 Y 坐标
            //Tx.scale(0.7+Math.random(), 0.7+Math.random());
            //x坐标方向的缩放倍数，y坐标方向的缩放倍数
            graphics2D.setTransform(Tx);
            Color c = new Color(rdm.nextInt(255), rdm.nextInt(255), rdm.nextInt(255));
            graphics2D.setColor(c);
            graphics2D.drawString(str, 2+i*imgWidth/4, imgHeigth-13);
        }*/

        // 输出到硬盘
        ImageIO.write(bufferedImage,"JPEG",new FileOutputStream("/home/joah/图片/verCode.jpg"));
    }


//    @Test
    /**
     * 得到验证码的文本形式
     */
    public String getTxt(){
        String txt="";
        for (int i = 0; i < 4; i++) {
            int idx=rdm.nextInt(codes.length());
            txt += codes.charAt(idx);
        }
        System.out.println(txt+" : "+txt.length());
        return txt;
    }
//    @Test
    public void getLine(BufferedImage bufferedImage){
        Graphics2D graphics2D= (Graphics2D) bufferedImage.getGraphics();
        for (int i = 0; i < 4; i++) {
            int x1=rdm.nextInt(imgWidth);
            int y1=rdm.nextInt(imgHeigth);
            int x2=rdm.nextInt(imgWidth);
            int y2=rdm.nextInt(imgHeigth);
            graphics2D.setStroke(new BasicStroke(1.7F));
            graphics2D.setColor(Color.GREEN);
            graphics2D.drawLine(x1,y1,x2,y2);
        }
    }
    public void getYawpPoint(BufferedImage bufferedImage){
        // setting yawpRate
        float yawpRate=0.05f;
        int area= (int) (yawpRate*imgWidth*imgHeigth);
        for (int i = 0; i < area; i++) {
            int x=rdm.nextInt(imgWidth);
            int y=rdm.nextInt(imgHeigth);
            int rgb=getRandomIntColor();
            bufferedImage.setRGB(x,y,rgb);
        }
    }
    private  int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private  int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = rdm.nextInt(255);
        }
        return rgb;
    }

}
