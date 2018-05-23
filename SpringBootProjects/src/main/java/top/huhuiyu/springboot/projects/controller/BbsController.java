package top.huhuiyu.springboot.projects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import top.huhuiyu.springboot.projects.dao.TbBbsDAO;
import top.huhuiyu.springboot.projects.entity.TbBbs;
import top.huhuiyu.springboot.projects.model.BbsModel;
import top.huhuiyu.springboot.projects.util.JsonMessage;
import top.huhuiyu.springboot.projects.util.MyUtils;

@RestController
@RequestMapping("/bbs")
public class BbsController {

  @Autowired
  private TbBbsDAO tbBbsDAO;

  @RequestMapping("/query")
  public JsonMessage query(BbsModel model) throws Exception { 
    PageHelper.startPage(model.getPage().getPageNumber(), model.getPage().getPageSize());
    JsonMessage message = JsonMessage.getSuccess("查询完成");
    Page<TbBbs> list = (Page<TbBbs>) tbBbsDAO.query();
    message.putData("list", list);
    model.getPage().setPageInfo(list);
    message.putData("page", model.getPage());
    return message;
  }

  @RequestMapping("/add")
  public JsonMessage add(BbsModel model) throws Exception {
    if (model.getBbs() == null) {
      return JsonMessage.getFail("信息必须填写");
    }
    if (MyUtils.strIsEmpty(model.getBbs().getUsername())) {
      return JsonMessage.getFail("用户名必须填写");
    }
    if (model.getBbs().getUsername().trim().length() > 8) {
      return JsonMessage.getFail("用户名不能超过8个字符");
    }
    if (MyUtils.strIsEmpty(model.getBbs().getInfo())) {
      return JsonMessage.getFail("留言必须填写");
    }
    if (model.getBbs().getInfo().trim().length() > 140) {
      return JsonMessage.getFail("用户名不能超过140个字符");
    }
    tbBbsDAO.add(model.getBbs());
    return JsonMessage.getSuccess("发帖成功。。。");
  }

}
