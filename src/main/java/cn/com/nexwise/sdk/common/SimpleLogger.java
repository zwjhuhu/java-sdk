package cn.com.nexwise.sdk.common;

public class SimpleLogger {
	
	
	private static final String DEBUG = "DEBUG";
	
	private static final String INFO = "INFO";
	
	private static final String WARN = "WARN";
	
	private static final String ERROR = "ERROR";
	
	private String prefix;
	
	public SimpleLogger(Class<?> clz) {
		this.prefix = clz.getName() + " [level] ";
	}
	
	public void debug(String msg) {
		this.debug(msg,new Object[] {});
	}
	
	public void debug(String format,Object... args) {
		this.log(DEBUG, format, args);
	}
	
	public void debug(String msg,Throwable t) {
		this.log(DEBUG, msg, t);
	}
	
	public void info(String msg) {
		this.debug(msg,new Object[] {});
	}
	
	public void info(String format,Object... args) {
		this.log(INFO, format, args);
	}
	
	public void info(String msg,Throwable t) {
		this.log(INFO, msg, t);
	}
	
	public void warn(String msg) {
		this.debug(msg,new Object[] {});
	}
	
	public void warn(String format,Object... args) {
		this.log(WARN, format, args);
	}
	
	public void warn(String msg,Throwable t) {
		this.log(WARN, msg, t);
	}
	
	public void error(String msg) {
		this.debug(msg,new Object[] {});
	}
	
	public void error(String format,Object... args) {
		this.log(ERROR, format, args);
	}
	
	public void error(String msg,Throwable t) {
		this.log(ERROR, msg, t);
	}
	
	private void log(String level,String format,Object... args) {
		if(ApiClient.getConfig()==null || !ApiClient.getConfig().showLog) {
			return;
		}
		StringBuilder sb = new StringBuilder(prefix.replace("[level]", "["+ level+"]"));
		if(format!=null && args != null && args.length > 0) {
			for(int i=0,len = args.length;i<len;i++) {
				format = format.replaceFirst("\\{\\}", args[i]==null?"null":args[i].toString());
			}
		}
		sb.append(format);
		System.out.println(sb.toString());
	}
	
	private void log(String level,String msg,Throwable t) {
		if(ApiClient.getConfig()==null || !ApiClient.getConfig().showLog) {
			return;
		}
		StringBuilder sb = new StringBuilder(prefix.replace("[level]", "["+ level+"]"));
		sb.append(msg);
		System.out.println(sb.toString());
		t.printStackTrace(System.out);
	}
}
