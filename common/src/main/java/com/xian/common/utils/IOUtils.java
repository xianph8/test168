package com.xian.common.utils;


import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
/**
 * IO操作类
 */
public class IOUtils {
    /**
     * 关闭流
     *
     * @param io l
     * @return b
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    /**
     * 将字符串转换为io流
     *
     * @param str d
     * @return s
     */
    public static InputStream StringToIO(String str) {
        InputStream is = new ByteArrayInputStream(str.getBytes(Charset.forName("utf-8")));
//		int byteRead;
//		try {
//			while ((byteRead = is.read()) != -1) {
//                System.out.print((char)byteRead);
//            }
//			is.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//			close(is);
//		}
//		System.out.println();
        return is;
    }
}