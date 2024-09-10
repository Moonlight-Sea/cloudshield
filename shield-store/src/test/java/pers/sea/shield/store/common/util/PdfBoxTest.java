package pers.sea.shield.store.common.util;


import java.io.IOException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

/**
 * pdf解析
 *
 * @author moon on 2024/5/13 by idea
 */
public class PdfBoxTest {


    @Test
    public void readPdf() {
        // 读取PDF文件路径
        String pdfFilePath = "D:\\Users\\moon\\Downloads\\国民经济行业分类2017.pdf";

        try (PDDocument document = Loader.loadPDF(new RandomAccessReadBufferedFile(pdfFilePath))) {
            // 创建一个PDF文本提取器对象
            PDFTextStripper pdfStripper = new PDFTextStripper();
            int pages = document.getNumberOfPages();
            System.out.println("PDF文档页数：" + pages);

            // for (int i = 10; i <= 92; i++) {
                pdfStripper.setStartPage(10);
                pdfStripper.setEndPage(92);

                // 获取PDF文档的文本内容
                String text = pdfStripper.getText(document);

                // 输出文本内容
                System.out.println("PDF文档内容：\n" + text);
            // }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
