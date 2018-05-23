package top.huhuiyu.springboot.projects.entity;

import java.io.Serializable;
import java.util.Date;

public class TbBbs implements Serializable {

  private static final long serialVersionUID = 8282084002895293817L;
  private int bbsId;
  private String username;
  private String info;
  private int laud;
  private Date createDate;

  public TbBbs() {
  }

  public int getBbsId() {
    return bbsId;
  }

  public void setBbsId(int bbsId) {
    this.bbsId = bbsId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public int getLaud() {
    return laud;
  }

  public void setLaud(int laud) {
    this.laud = laud;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return "TbBbs [bbsId=" + bbsId + ", username=" + username + ", info=" + info + ", laud=" + laud + ", createDate="
        + createDate + "]";
  }

}
