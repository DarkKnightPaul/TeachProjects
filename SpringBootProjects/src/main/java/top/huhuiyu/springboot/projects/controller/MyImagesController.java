package top.huhuiyu.springboot.projects.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationHome;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import top.huhuiyu.springboot.projects.Application;
import top.huhuiyu.springboot.projects.dao.TbMyImagesDAO;
import top.huhuiyu.springboot.projects.entity.TbMyImages;
import top.huhuiyu.springboot.projects.model.MyImagesModel;
import top.huhuiyu.springboot.projects.util.JsonMessage;

@RestController
@RequestMapping("/myimages")
public class MyImagesController {
  public static final String HTML_DIR = "/static/html";
  public static final String UPLOAD_DIR = "/uploadfile/";
  
  @Autowired
  private TbMyImagesDAO tbMyImagesDAO;

  private static File getHomePath() {
    ApplicationHome home = new ApplicationHome(Application.class);
    File jarFile = home.getSource();
    if (jarFile == null) {
      return new File("").getAbsoluteFile();
    }
    return jarFile.getParentFile();
  }

  @RequestMapping("/upload")
  @ResponseBody
  public JsonMessage upload(MyImagesModel model, MultipartFile file) throws Exception {
    if (file == null) {
      return JsonMessage.getFail("请选择要上传的图片");
    }
    if (model.getImages() == null || model.getImages().getDescription() == null) {
      return JsonMessage.getFail("请输入图片描述信息");
    }
    if (!file.getContentType().toLowerCase().startsWith("image/")) {
      return JsonMessage.getFail("必须是图片");
    }
    // 处理文件保存
    String extname = file.getOriginalFilename();
    extname = extname.substring(extname.lastIndexOf("."));

    String savePath = UPLOAD_DIR + UUID.randomUUID() + extname; // 服务器路径
    File saveFile = new File(getHomePath() + HTML_DIR, savePath); // 文件保存位置
    if (!saveFile.getParentFile().exists()) { // 确保路径存在
      saveFile.getParentFile().mkdirs();
    }
    InputStream is = file.getInputStream();
    OutputStream os = new FileOutputStream(saveFile);
    byte[] bytes = new byte[8 * 1024];
    int len = is.read(bytes);
    while (len > 0) {
      os.write(bytes, 0, len);
      os.flush();
      len = is.read(bytes);
    }
    os.close();
    is.close();

    // 处理数据库保存
    TbMyImages images = model.getImages();
    images.setFilename(file.getOriginalFilename());
    images.setFilesize(file.getSize());
    images.setMime(file.getContentType());
    images.setSavePath(savePath);
    tbMyImagesDAO.add(images);
    JsonMessage message = JsonMessage.getSuccess("上传成功");
    message.putData("images", images);
    return message;
  }

  @RequestMapping("/queryLast")
  @ResponseBody
  public JsonMessage queryLast() throws Exception {
    JsonMessage message = JsonMessage.getSuccess("查询完成");
    message.putData("list", tbMyImagesDAO.queryLast());
    return message;
  }

}
