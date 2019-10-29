package com.cms.cn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationTests {

    @Test
    public void contextLoads() {
//        readFile();
    }

    /**
     * 读入TXT文件
     */
    public static void readFile() {
        String pathname = "E:\\cdf99.com.txt";
        try
                (FileReader reader = new FileReader(pathname);
                 BufferedReader br = new BufferedReader(reader)
                         )
             {
                 String line;
                 while ((line = br.readLine()) != null) {
                 System.out.println(line);
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
    }

}
