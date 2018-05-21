package top.huhuiyu.springboot.projects.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MyIntegerConverter implements Converter<String, Integer> {

  @Override
  public Integer convert(String source) {
    if (source == null || source.trim().equals("")) {
      return 0;
    }
    try {
      return Integer.parseInt(source);
    } catch (Exception e) {
      return 0;
    }
  }

}
