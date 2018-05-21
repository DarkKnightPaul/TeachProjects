package top.huhuiyu.springboot.projects.model;

import top.huhuiyu.springboot.projects.entity.TbMyImages;

public class MyImagesModel extends BaseModel{
  private static final long serialVersionUID = -1070631681866836709L;
  private TbMyImages images;
  
  public MyImagesModel() {
  }

  public TbMyImages getImages() {
    return images;
  }

  public void setImages(TbMyImages images) {
    this.images = images;
  }
  
}
