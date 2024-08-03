package com.xiaou.xiaoueasyprojectbackend.module.support.mobaijun;




import com.xiaou.xiaoueasyprojectbackend.module.support.mobaijun.model.WallpaperData;
import com.xiaou.xiaoueasyprojectbackend.module.support.mobaijun.util.FileUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.mobaijun.util.JsonUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.mobaijun.util.WallpaperJsonWriter;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * @Author: Xiaou61
 * @Date: 2024/08/03 14:08
 * @deprecated 一个每日自动拉取壁纸写入readme文件的程序
 */

public class WallpaperApplication {

    /**
     * <a href="https://wallhaven.cc/help/api">WALLPAPER_API</a>
     */
    private static final String WALLPAPER_API = "https://wallhaven.cc/api/v1/search?sorting=toplist&ref=fp&atleast=1920x1080&ratios=16x9";
    /**
     * 当前日期作为分页参数
     */
    private static final String PAGE_API = WALLPAPER_API + "&page=" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);


    public static void main(String[] args) throws IOException {
        //这里需要打开代理，不然会报错
        List<WallpaperData> wallpaperData = JsonUtils.getWallpaperList(PAGE_API, true);
        FileUtils.writeWallpaper(wallpaperData);
        FileUtils.writeReadme(wallpaperData);
        FileUtils.writeMonthInfo(wallpaperData);
        FileUtils.determineSizeFile();
        // 写入到 json
        WallpaperJsonWriter.writeJsonFile(wallpaperData);
    }
}