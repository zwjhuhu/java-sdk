package cn.com.nexwise.sdk.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.nexwise.sdk.common.dto.ApiRequest;
import cn.com.nexwise.sdk.common.dto.ApiResult;

public abstract class AbstractTask {
	
	protected RestInfo restInfo;
		  
	  protected abstract RestInfo initRestInfo();
	  
	  protected Map<String, Parameter> parameterMap;
	  
	  public AbstractTask(ApiRequest req) {
		  initializeParameters(req);
		  restInfo = initRestInfo();
	  }
	  	  
	  private void initializeParameters(ApiRequest req) {
		  parameterMap = new HashMap<>();
		  	if(req==null) {
		  		return;
		  	}
		  	try {
		  		List<Field> fields = getAllFields(req);
		  		for (Field f : fields) {
		  			f.setAccessible(true);
		  			Param at = f.<Param>getAnnotation(Param.class);
		  			Parameter p = new Parameter();
		  			p.name = f.getName();
		  			p.annotation = at;
		  			p.value = f.get(req);
		  			if(at!=null) {
		  				p.type = p.annotation.paramType();
		  				if(p.type==null) {
		  					p.type = Parameter.TYPE_BODY;
		  				}else {
		  					p.type = p.type.toLowerCase();
		  				}
		  				parameterMap.put(p.name, p);
		  			}
		  		} 
		  		commonParameterMerge(req);
		  	}catch (Exception e) {
				new ApiException(Constants.INTERNAL_ERROR,e);
			}
	  }
	  
	  
	  
	protected void commonParameterMerge(ApiRequest req) {
		Parameter p = new Parameter();
		p.name = "accessToken";
		if(req.getAccessToken()!=null && !req.getAccessToken().trim().isEmpty()) {
  			p.value = req.getAccessToken().trim();
  			p.type = Parameter.TYPE_HEADER;
			parameterMap.put(p.name,p);
		}else {
			throw new ApiException(String.format("missing required field [%s]", p.name));
		}
		
		p = new Parameter();
		p.name = "accessKeySecret";
		if(req.getAccessKeySecret()!=null && !req.getAccessKeySecret().trim().isEmpty()) {
  			p.value = req.getAccessKeySecret().trim();
  			p.type = Parameter.TYPE_HEADER;
			parameterMap.put(p.name,p);
		}else {
			throw new ApiException(String.format("missing required field [%s]", p.name));
		}
	}

	private List<Field> getAllFields(ApiRequest req) {
		    Class<?> c = req.getClass();
		    List<Field> fs = new ArrayList<>();
		    while (c != Object.class) {
			    Collections.addAll(fs, c.getDeclaredFields());
			    c = c.getSuperclass();
		    } 
		    return fs;
	  }
	  
//	  Set<String> getAllParameterNames() {
//	    initializeParametersIfNot();
//	    return getParameterMap().keySet();
//	  }
//	  
	  public Object getParameterValue(String name) {
	    return getParameterValue(name, true);
	  }
//	  
	  
	  public Object getParameterValue(String name, boolean exceptionOnNotFound) {
	    Parameter p = parameterMap.get(name);
	    if (p == null) {
	      if (exceptionOnNotFound)
	        throw new ApiException(String.format("field [%s] is not found", name)); 
	      return null;
	    } 
	    
	    return p.value;
	  }
	  
	  public Map<String,Object> getAllQueryParameterValues() {
		  return getAllParameterValuesByType(Parameter.TYPE_QUERY);
	  }
	  
	  public Map<String,Object> getAllBodyParameterValues() {
		  return getAllParameterValuesByType(Parameter.TYPE_BODY);
	  }
	  
	  public Map<String,Object> getAllFormParameterValues() {
		  return getAllParameterValuesByType(Parameter.TYPE_FORM);
	  }
	  
	  private Map<String,Object> getAllParameterValuesByType(String type) {
		  Map<String,Object> map = new HashMap<>();
		  parameterMap.forEach((name,p) ->{
			  if(p.type.equalsIgnoreCase(type) && p.value!=null) {
				  map.put(name, p.value);
			  }
		  });
		  return map;
	  }
	  
	  public void checkParameters() {
	    try {
	      ParameterChecker.checkParams(parameterMap.values());
	    } catch (ApiException e) {
	      throw e;
	    } catch (Exception e) {
	      throw new ApiException(e);
	    } 
	  }

	  /**
	   * 处理返回值
	   * @param str
	   */
	protected abstract ApiResult translateResult(String str);
	
}
