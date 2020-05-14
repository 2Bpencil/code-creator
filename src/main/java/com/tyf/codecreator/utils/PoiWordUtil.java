package com.tyf.codecreator.utils;

import org.apache.poi.util.Units;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Description: 操作word
 * @Author: tyf
 * @Date: 2019/11/8 17:38
 */
public class PoiWordUtil {

    /** 
    * @Description: 创建一个word文档对象 
    * @Param:
    * @return:  
    * @Author: Mr.Tan 
    * @Date: 2019/11/8 17:58
    */ 
    public static XWPFDocument createDocument(){
        return new XWPFDocument();
    }


    public static void main(String[] args)  throws Exception{
        //创建一个word文档对象
        XWPFDocument doc = createDocument();

        //创建段落对象
        XWPFParagraph p = doc.createParagraph();
        //设置对齐
        p.setAlignment(ParagraphAlignment.CENTER);
        //底部边框
        p.setBorderBottom(Borders.SINGLE);
        //顶部边框
        p.setBorderTop(Borders.SINGLE);
        //右边框
        p.setBorderRight(Borders.SINGLE);
        //左边框
        p.setBorderLeft(Borders.SINGLE);
        //水平对齐
        p.setVerticalAlignment(TextAlignment.CENTER);

        //创建文本对象
        XWPFRun r = p.createRun();
        r.addBreak();
        //添加图片
        String imgPath = "C:\\Users\\Administrator\\Pictures\\xs.jpg";
        r.addPicture(new FileInputStream(imgPath), XWPFDocument.PICTURE_TYPE_JPEG, imgPath, Units.toEMU(375), Units.toEMU(250));
        r.addBreak();

        //写入文本内容
        r.setText("红豆生南国，春来发几枝。");
        r.addBreak();
        r.setText("愿君多采撷，此物最相思。");
        r.addBreak();
        //加粗
        r.setBold(true);
        //设置颜色  16进制（黑色）
        r.setColor("000000");
        //设置字体
        r.setFontFamily("Courier");
        //设置字体大小
        r.setFontSize(14);
        //设置下划线
        r.setUnderline(UnderlinePatterns.NONE);
        r.setTextPosition(10);


        //添加页眉页脚
        XWPFHeader head = doc.createHeader(HeaderFooterType.DEFAULT);
        XWPFParagraph headParagraph = head.createParagraph();
        headParagraph.setVerticalAlignment(TextAlignment.CENTER);
        headParagraph.setAlignment(ParagraphAlignment.CENTER);
        headParagraph.createRun().setText("我是页眉");
        XWPFFooter foot = doc.createFooter(HeaderFooterType.DEFAULT);
        XWPFParagraph footParagraph = foot.createParagraph();
        footParagraph.setVerticalAlignment(TextAlignment.CENTER);
        footParagraph.setAlignment(ParagraphAlignment.CENTER);
        footParagraph.createRun().setText("我是页脚");

        FileOutputStream out = new FileOutputStream("F:\\test\\doc\\simple.docx");
        doc.write(out);
        out.close();
        doc.close();

    }






}
    