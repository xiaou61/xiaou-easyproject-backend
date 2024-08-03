package com.xiaou.xiaoueasyprojectbackend.module.support.mobaijun.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import com.xiaou.xiaoueasyprojectbackend.module.support.mobaijun.model.WallpaperData;


import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class WallpaperJsonWriter {
    private static final String JSON_FILE_PATH = "api.json";

    private static final Log log = Log.get(WallpaperJsonWriter.class);

    /**
     * 配置 json api
     *
     * @param wallpaperData 地址列表
     */
    public static void writeJsonFile(List<WallpaperData> wallpaperData) {
        try {
            // 读取已有的JSON文件内容
            List<String> existingJsonEntries = readExistingJsonFile();
            // 选择前24个URL（如果有的话）
            List<String> newEntries = wallpaperData.stream()
                    .limit(24)
                    .map(WallpaperData::getPath)
                    .toList();
            Path path = Paths.get(JSON_FILE_PATH);
            if (existingJsonEntries.isEmpty()) {
                FileUtil.writeString(JSONUtil.toJsonStr(newEntries), path.toFile(), StandardCharsets.UTF_8);
            } else {
                // 去重并写入JSON文件
                existingJsonEntries.addAll(newEntries);
                List<String> distinctList = existingJsonEntries.stream().distinct().toList();
                FileUtil.writeString(JSONUtil.toJsonStr(distinctList), path.toFile(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            log.error("文件写入异常，请定位：", e);
        }
    }

    private static List<String> readExistingJsonFile() {
        try {
            // 读取已有的JSON文件内容
            String jsonContent = FileUtil.readString(Paths.get(JSON_FILE_PATH).toFile(), StandardCharsets.UTF_8);
            // 提取URL并返回列表
            return JSONUtil.toList(jsonContent, String.class);
        } catch (Exception e) {
            return List.of();
        }
    }
}
