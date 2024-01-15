package pers.sea.shield.common.core.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FileUtilTest {

  static final String path = "F:\\数据结构与算法之美";

  List<String> targetNameList = Arrays.asList("百度云SVIP长期免费使用.url",
      "本教程由我爱学it提供.url",
      "高清电子书籍.url", "更多精品教程.url", "下载必看.txt");

  void deleteFile(File file) {
    File[] fs = file.listFiles();     //遍历path下的文件和目录，放在File数组中
    assert fs != null;
    for (File f : fs) {
      String fileName = f.getName();  //获取文件和目录名
      if (f.isDirectory())    //若是目录，则递归打印该目录下的文件
      {
        deleteFile(f);
      }
      System.out.println(f.getName());
      if (targetNameList.contains(fileName)) {  //另外可用fileName.endsWith("txt")来过滤出以txt结尾的文件
        f.deleteOnExit();
      }
    }
  }

  @Test
  void batchDeleteFile() {
    File file = new File(path);      //获取其file对象
    deleteFile(file);
  }
}
