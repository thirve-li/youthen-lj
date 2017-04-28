// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * 数据录入工具类
 * 
 * @author wdc
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class JavaCvsUtils {

    /**
     * 读取数据
     * 。
     */
    public static void read() {

        final String filePath = "D:/ljwy.csv";

        try {
            // 创建CSV读对象
            final CsvReader csvReader = new CsvReader(filePath);

            // 读表头
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                // 读一整行
                System.out.println(csvReader.getRawRecord());
                // 读这行的某一列
                System.out.println(csvReader.get("Link"));
            }

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入数据
     * 。
     */
    public static void write() {

        final String filePath = "D:/ljwy.csv";

        try {
            // 创建CSV写对象
            final CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("GBK"));
            // CsvWriter csvWriter = new CsvWriter(filePath);

            // 写表头
            final String[] headers = {"室号", "建筑面积", "物业费(元/㎡)", "元/月"};
            final String[] content = {"MLY-1-101", "112.22", "2.45", "274.9"};
            csvWriter.writeRecord(headers);
            csvWriter.writeRecord(content);
            csvWriter.close();

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        final JavaCvsUtils javaCvs = new JavaCvsUtils();
        javaCvs.read();
    }

}
