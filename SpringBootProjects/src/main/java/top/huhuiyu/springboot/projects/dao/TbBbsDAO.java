package top.huhuiyu.springboot.projects.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import top.huhuiyu.springboot.projects.entity.TbBbs;

@Mapper
public interface TbBbsDAO {
  public int add(TbBbs bbs) throws Exception;

  public List<TbBbs> query() throws Exception;
}
