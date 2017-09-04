package test;

import cn.itcast.vcode.utils.VerifyCode;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class testItcastTool {
    @Test
    public void verificationCode() throws IOException {

        VerifyCode verifyCode=new VerifyCode();
        BufferedImage bufferedImag=verifyCode.getImage();
        System.out.println(verifyCode.getText());

        verifyCode.output(bufferedImag,new FileOutputStream("/home/joah/图片/vcode.jpg"));
    }
}
