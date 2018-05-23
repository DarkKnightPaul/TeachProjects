package top.huhuiyu.springboot.projects.util;

public class MyUtils {
  
  public static boolean strIsEmpty(String str) {
    return (str==null || str.trim().equals("")); 
  }
}
