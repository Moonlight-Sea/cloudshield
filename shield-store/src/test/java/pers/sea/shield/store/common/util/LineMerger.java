package pers.sea.shield.store.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

public class LineMerger {

    private final Pattern pattern = Pattern.compile("^([A-Z]+|[ ]+[0-9]+)");


    @Test
    public void mach(){
        String line = "    农、林、牧、渔业   本门类包括 01～05 大类 ";
        if (pattern.matcher(line).find()) {
            System.out.println("匹配成功");
        }else {
            System.out.println("匹配失败");
        }
    }
    @Test
    public void mergerLine() {
        String inputFilePath = "D:\\Users\\moon\\Downloads\\industray.txt";
        String outputFilePath = "D:\\Users\\moon\\Downloads\\outputfile.txt";

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFilePath));
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
            StringBuilder currentLine = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!currentLine.isEmpty()) {
                    currentLine.append(" "); // 添加空格来拼接行
                }

                // 检查行是否以句号结尾
                if (!pattern.matcher(line).find()) {
                    currentLine.append(line);
                } else {
                    writer.newLine();
                    writer.write(currentLine.toString());
                    currentLine.delete(0, currentLine.length());

                    currentLine.append(line);
                }
            }

            // 写入最后的拼接行（如果有的话）
            if (!currentLine.isEmpty()) {
                writer.write(currentLine.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

