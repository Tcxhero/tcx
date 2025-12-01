package com.feisuanyz.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

/**
 * 文件工具类
 *
 * @author tianchunxu
 */
@Slf4j
public class FileUtil {

    /**
     * 生成唯一文件名
     *
     * @param originalFileName 原始文件名
     * @return 唯一文件名
     */
    public static String generateUniqueFileName(String originalFileName) {
        String extension = "";
        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex > 0) {
            extension = originalFileName.substring(dotIndex);
        }
        return UUID.randomUUID().toString().replace("-", "") + extension;
    }

    /**
     * 创建目录
     *
     * @param dirPath 目录路径
     * @return 是否创建成功
     */
    public static boolean mkdir(String dirPath) {
        try {
            Files.createDirectories(Paths.get(dirPath));
            return true;
        } catch (IOException e) {
            log.error("创建目录失败：{}", dirPath, e);
            return false;
        }
    }

    /**
     * 写入文件
     *
     * @param filePath 文件路径
     * @param content  文件内容
     * @return 是否写入成功
     */
    public static boolean writeFile(String filePath, byte[] content) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(content);
            return true;
        } catch (IOException e) {
            log.error("写入文件失败：{}", filePath, e);
            return false;
        }
    }

    /**
     * 获取文件MD5值
     *
     * @param content 文件内容
     * @return MD5值
     */
    public static String getFileMd5(byte[] content) {
        return DigestUtils.md5DigestAsHex(content);
    }

    /**
     * 获取文件大小
     *
     * @param filePath 文件路径
     * @return 文件大小
     */
    public static long getFileSize(String filePath) {
        File file = new File(filePath);
        return file.exists() ? file.length() : 0L;
    }

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     * @return 是否删除成功
     */
    public static boolean deleteFile(String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
            return true;
        } catch (IOException e) {
            log.error("删除文件失败：{}", filePath, e);
            return false;
        }
    }

    /**
     * 判断文件是否为支持的格式
     *
     * @param fileName 文件名
     * @return 是否支持
     */
    public static boolean isSupportedFormat(String fileName) {
        String lowerFileName = fileName.toLowerCase();
        return lowerFileName.endsWith(".mp4") || lowerFileName.endsWith(".mov");
    }
}