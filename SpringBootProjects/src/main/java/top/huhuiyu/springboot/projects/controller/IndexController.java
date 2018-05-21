package top.huhuiyu.springboot.projects.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.huhuiyu.springboot.projects.util.JsonMessage;

@RestController
public class IndexController {

  @RequestMapping("/")
  public JsonMessage index() {
    return JsonMessage.getSuccess("欢迎使用SpringBoot数据微服务");
  }
  
}
