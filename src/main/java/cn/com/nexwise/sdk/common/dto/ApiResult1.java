package cn.com.nexwise.sdk.common.dto;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;

import cn.com.nexwise.sdk.common.ApiException;
import cn.com.nexwise.sdk.common.SourceClassMap;
import cn.com.nexwise.sdk.common.ZSClient;

public class ApiResult1 {
  public ErrorCode error;
  
  private String resultString;
  
  public ErrorCode getError() {
    return this.error;
  }
  
  void setError(ErrorCode error) {
    this.error = error;
  }
  
  public void setResultString(String resultString) {
    this.resultString = resultString;
  }
  
  private static Object getProperty(Object bean, Iterator<String> it) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    String path = it.next();
    if (bean instanceof Map) {
      Pattern re = Pattern.compile("(.*)\\[(\\d+)]");
      Matcher m = re.matcher(path);
      if (m.find())
        path = String.format("(%s)[%s]", new Object[] { m.group(1), m.group(2) }); 
    } 
    Object val = PropertyUtils.getProperty(bean, path);
    if (it.hasNext())
      return getProperty(val, it); 
    return val;
  }
  
  public static Object getProperty(Object bean, String path) {
    List<String> paths = Arrays.asList(path.split("\\."));
    try {
      return getProperty(bean, paths.iterator());
    } catch (Exception e) {
      throw new RuntimeException(e);
    } 
  }
  
  private static void setProperty(Object bean, Iterator<String> it, String fieldName, Object val) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    if (it.hasNext())
      bean = getProperty(bean, it); 
    if (bean instanceof Map) {
      Pattern re = Pattern.compile("(.*)\\[(\\d+)]");
      Matcher m = re.matcher(fieldName);
      if (m.find())
        fieldName = String.format("(%s)[%s]", new Object[] { m.group(1), m.group(2) }); 
    } 
    PropertyUtils.setProperty(bean, fieldName, val);
  }
  
  public static void setProperty(Object bean, String path, Object val) {
    List<String> paths = Arrays.asList(path.split("\\."));
    String fieldName = paths.get(paths.size() - 1);
    paths = paths.subList(0, paths.size() - 1);
    try {
      setProperty(bean, paths.iterator(), fieldName, val);
    } catch (Exception e) {
      throw new RuntimeException(e);
    } 
  }
  
  public <T> T getResult(Class<T> clz) {
    if (this.resultString == null || this.resultString.isEmpty())
      return null; 
    Map m = (Map)ZSClient.gson.fromJson(this.resultString, LinkedHashMap.class);
    T ret = (T)ZSClient.gson.fromJson(this.resultString, clz);
    if (!m.containsKey("schema"))
      return ret; 
    Map<String, String> schema = (Map<String, String>)m.get("schema");
    try {
      for (String path : schema.keySet()) {
        String src = schema.get(path);
        String dst = SourceClassMap.srcToDstMapping.get(src);
        if (dst == null)
          continue; 
        Object bean = getProperty(ret, path);
        if (bean.getClass().getName().equals(dst))
          continue; 
        Class<?> dstClz = Class.forName(dst);
        Object source = getProperty(m, path);
        Object dstBean = ZSClient.gson.fromJson(ZSClient.gson.toJson(source), dstClz);
        setProperty(ret, path, dstBean);
      } 
      return ret;
    } catch (Exception e) {
      throw new ApiException(e);
    } 
  }
}
