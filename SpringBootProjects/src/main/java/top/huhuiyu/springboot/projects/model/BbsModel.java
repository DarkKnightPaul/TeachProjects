package top.huhuiyu.springboot.projects.model;

import top.huhuiyu.springboot.projects.entity.TbBbs;

public class BbsModel extends BaseModel {

  private static final long serialVersionUID = -7001842467117776610L;

  private TbBbs bbs;

  public BbsModel() {
  }

  public TbBbs getBbs() {
    return bbs;
  }

  public void setBbs(TbBbs bbs) {
    this.bbs = bbs;
  }

}
