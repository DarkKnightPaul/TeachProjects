package top.huhuiyu.springboot.projects.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import top.huhuiyu.springboot.projects.entity.TbMyImages;

@Mapper
public interface TbMyImagesDAO {
  public int add(TbMyImages images) throws Exception;

  public int delete(TbMyImages images) throws Exception;
  
  public List<TbMyImages> queryLast() throws Exception;
}
