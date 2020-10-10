package com.bookstore.web.servlet;

import org.junit.Test;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/imageCode")
public class CheckImgServlet extends HttpServlet {

    private List<String> worlds = new ArrayList<String>();

    @Override
    public void init() throws ServletException {
        String path = getServletContext().getRealPath("\\WEB-INF\\new_words.txt");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
            String line;
            while ((line = reader.readLine()) != null){

                worlds.add(line);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int width = 120;
        int height = 30;

        //绘制一张内存中的图片
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //绘制背景颜色

        Graphics graphics = bufferedImage.getGraphics();

        graphics.setColor(getRandColor(200,250));
        graphics.fillRect(0,0,width,height);

        graphics.setColor(Color.white);
        graphics.drawRect(0,0,width - 1,height -1);
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics.setFont(new Font("宋体",Font.BOLD,18));

        Random random = new Random();
        int index = random.nextInt(worlds.size());
        String word = worlds.get(index);
        // 定义x坐标
        int x = 10;
        for (int i = 0; i < word.length(); i++) {
            // 随机颜色
            graphics2D.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            // 旋转 -30 --- 30度
            int jiaodu = random.nextInt(60) - 30;
            // 换算弧度
            double theta = jiaodu * Math.PI / 180;

            // 获得字母数字
            char c = word.charAt(i);

            // 将c 输出到图片
            graphics2D.rotate(theta, x, 20);
            graphics2D.drawString(String.valueOf(c), x, 20);
            graphics2D.rotate(-theta, x, 20);
            x += 30;
        }

        System.out.println(word);
        req.getSession().setAttribute("checkcode_session",word);

        // 步骤五 绘制干扰线
        graphics.setColor(getRandColor(160, 200));
        int x1;
        int x2;
        int y1;
        int y2;
        for (int i = 0; i < 30; i++) {
            x1 = random.nextInt(width);
            x2 = random.nextInt(12);
            y1 = random.nextInt(height);
            y2 = random.nextInt(12);
            graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
        }

        graphics.dispose();//释放资源
        ImageIO.write(bufferedImage,"jpg",resp.getOutputStream());



    }

    private Color getRandColor(int fc,int bc){

        Random random = new Random();

        if (fc>255){
            fc = 255;
        }
        if (bc > 255){

            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc -fc);

        return new Color(r,g,b);
    }

}
