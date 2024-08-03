package com.xiaou.xiaoueasyprojectbackend.module.support.mobaijun.model;


public class Thumbs {

    /**
     * 432 * 243
     */
    private String large;

    /**
     * 300 * 294
     */
    private String original;

    /**
     * 300 * 200
     */
    private String small;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    @Override
    public String toString() {
        return "Thumbs{" +
                "large='" + large + '\'' +
                ", original='" + original + '\'' +
                ", small='" + small + '\'' +
                '}';
    }
}
