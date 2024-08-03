package com.xiaou.xiaoueasyprojectbackend.module.support.mobaijun.model;


public class WallpaperData {
    private String id;
    private String url;
    private String fileType;
    private String createdAt;
    /**
     * 尺寸
     */
    private String resolution;
    private String path;
    private Thumbs thumbs;

    /**
     * 格式化 markdown 文本
     *
     * @return 字符串
     */
    public String formatMarkdown() {
        return String.format("%s | [%s download 4k](%s) | [thumbs](%s)", createdAt, id, url, thumbs.getSmall());
    }

    /**
     * 生成 Readme 标题
     *
     * @return 字符串
     */
    public String toLarge() {
        return String.format("![%s](%s) Today: [%s](%s)", id, path, id, thumbs.getSmall());
    }

    /**
     * 生成空格内文本
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("![%s](%s)[%s download 4k](%s)", id, thumbs.getSmall(), id, url);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Thumbs getThumbs() {
        return thumbs;
    }

    public void setThumbs(Thumbs thumbs) {
        this.thumbs = thumbs;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}
