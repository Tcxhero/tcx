package com.feisuanyz.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 视频工具类
 *
 * @author tianchunxu
 */
@Slf4j
@Component
public class VideoUtil {

    /**
     * 合并分片文件
     *
     * @param outputFilePath 输出文件路径
     * @param chunkPaths     分片文件路径列表
     * @return 是否合并成功
     */
    public static boolean mergeChunks(String outputFilePath, List<String> chunkPaths) {
        try {
            File outputFile = new File(outputFilePath);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            try (java.io.FileOutputStream fos = new java.io.FileOutputStream(outputFile)) {
                for (String chunkPath : chunkPaths) {
                    File chunkFile = new File(chunkPath);
                    if (chunkFile.exists()) {
                        try (java.io.FileInputStream fis = new java.io.FileInputStream(chunkFile)) {
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = fis.read(buffer)) != -1) {
                                fos.write(buffer, 0, bytesRead);
                            }
                        }
                    }
                }
            }
            return true;
        } catch (IOException e) {
            log.error("合并分片文件失败：{}", outputFilePath, e);
            return false;
        }
    }

    /**
     * 获取视频时长（单位：秒）
     *
     * @param videoFilePath 视频文件路径
     * @return 视频时长
     */
    public static int getVideoDuration(String videoFilePath) {
        // 此处为模拟方法，实际应用中应调用FFmpeg等工具获取
        // 模拟返回固定值
        // 假设视频时长为120秒
        return 120;
    }

    /**
     * 从视频帧中提取封面图
     *
     * @param videoFilePath 视频文件路径
     * @param timeSecond    时间点（秒）
     * @param coverPath     封面图保存路径
     * @return 是否提取成功
     */
    public static boolean extractFrameAsCover(String videoFilePath, int timeSecond, String coverPath) {
        // 此处为模拟方法，实际应用中应调用FFmpeg等工具进行截图
        // 模拟创建一个假的封面图
        try {
            File coverFile = new File(coverPath);
            if (!coverFile.getParentFile().exists()) {
                coverFile.getParentFile().mkdirs();
            }
            // 创建一个空的封面图文件
            coverFile.createNewFile();
            return true;
        } catch (IOException e) {
            log.error("提取视频帧封面失败：{}", coverPath, e);
            return false;
        }
    }

    public static boolean exists(Long videoId) {
        // 实现逻辑，例如查询数据库中是否存在该视频ID
        // 这里仅作示例，实际需要调用视频服务或数据库访问层
        return videoId != null && videoId > 0;
    }
}
