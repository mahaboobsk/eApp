package com.founderr.founderr;

import java.io.Serializable;

public class Magazine implements Serializable {
    private Integer RID;
    private String MagazineName;
    private String PublishedDate;
    private String ThumbImage;
    private String Video;
    private Integer CategoryRID;
    private String MagazineContent;
    public Magazine(Integer RID,String MagazineName, String PublishedDate,String ThumbImage, String Video,Integer CategoryRID, String MagazineContent){
        this.RID = RID;
        this.MagazineName = MagazineName;
        this.PublishedDate = PublishedDate;
        this.ThumbImage = ThumbImage;
        this.Video = Video;
        this.CategoryRID = CategoryRID;
        this.MagazineContent = MagazineContent;
    }

    public Integer getRID() {
        return RID;
    }

    public void setRID(Integer RID) {
        this.RID = RID;
    }

    public String getMagazineName() {
        return MagazineName;
    }

    public void setMagazineName(String magazineName) {
        this.MagazineName = magazineName;
    }

    public String getPublishedDate() {
        return PublishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.PublishedDate = publishedDate;
    }

    public String getThumbImage() {
        return ThumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.ThumbImage = thumbImage;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        this.Video = video;
    }

    public Integer getCategoryRID() {
        return CategoryRID;
    }

    public void setCategoryRID(Integer categoryRID) {
        this.CategoryRID = categoryRID;
    }

    public String getMagazineContent() {
        return MagazineContent;
    }

    public void setMagazineContent(String magazineContent) {
        this.MagazineContent = magazineContent;
    }
}
