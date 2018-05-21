package top.huhuiyu.springboot.projects.entity;

import java.io.Serializable;
import java.util.Date;

public class TbMyImages implements Serializable {
  private static final long serialVersionUID = -8926040049704495218L;

  private int imageId;
  private String savePath;
  private String description;
  private String filename;
  private String mime;
  private long filesize;
  private Date uploadDate;

  public TbMyImages() {
  }

  public int getImageId() {
    return imageId;
  }

  public void setImageId(int imageId) {
    this.imageId = imageId;
  }

  public String getSavePath() {
    return savePath;
  }

  public void setSavePath(String savePath) {
    this.savePath = savePath;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getMime() {
    return mime;
  }

  public void setMime(String mime) {
    this.mime = mime;
  }

  public long getFilesize() {
    return filesize;
  }

  public void setFilesize(long filesize) {
    this.filesize = filesize;
  }

  public Date getUploadDate() {
    return uploadDate;
  }

  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }

  @Override
  public String toString() {
    return "TbMyImages [imageId=" + imageId + ", savePath=" + savePath + ", description=" + description + ", filename="
        + filename + ", mime=" + mime + ", filesize=" + filesize + ", uploadDate=" + uploadDate + "]";
  }

}
