package cn.com.nexwise.sdk.common;

/**
 * 参数封装
 * @author GDJC
 *
 */
public class Parameter {
	
	public static final String TYPE_FORM = "form";
	
	public static final String TYPE_PATH = "path";
	
	public static final String TYPE_QUERY = "query";
	
	public static final String TYPE_BODY = "body";
	
	public static final String TYPE_HEADER = "header";
	
    Object value;
    
    String name;
    
    String type;
    
    Param annotation;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Parameter [value=" + value + ", name=" + name + ", type=" + type + "]";
	}   
	
}
