package com.javaglm.language.ch17;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

/**
 * 第 17 章 · IO 与文件基础。
 * 够用即可：现代写法用 java.nio.file 的 Files / Path（别去啃老的 InputStream）。
 * 注意：文件操作会抛 IOException（受检异常），必须处理。
 */
public class IoDemo {

    public static void main(String[] args) throws IOException {
        // 文件写到 target/ 下（避免污染源码目录）
        Path file = Paths.get("target", "ch17-demo.txt");

        // 1. 写文件：一行写完（Java 8 没有 List.of，用 Arrays.asList）
        Files.write(file,
                Arrays.asList("第一行", "第二行"),
                StandardCharsets.UTF_8);

        // 2. 读文件：一次读全部行
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        lines.forEach(line -> System.out.println("读到：" + line));

        // 3. 追加写
        Files.write(file,
                Arrays.asList("第三行（追加）"),
                StandardCharsets.UTF_8,
                StandardOpenOption.APPEND);
        System.out.println("追加后总行数 = " + Files.readAllLines(file).size());
    }
}
