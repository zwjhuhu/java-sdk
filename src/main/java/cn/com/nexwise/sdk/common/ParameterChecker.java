package cn.com.nexwise.sdk.common;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ParameterChecker {
	
	private ParameterChecker() {}

	public static void checkParams(Collection<Parameter> params) {
		
		boolean typeBody = true;
		for (Parameter p : params) {
	        Object value = p.value;
	        Param at = p.annotation;
	        String pname = p.name;
	        if(at==null) {
	        	continue;
	        }
	        if (at.required() && value == null) {
	        	throw new ApiException(String.format("missing required field [%s]", pname)); 
	        }else if (value==null){
	        	continue;
	        }
	        
	        if(value instanceof String) {
	        	checkStringParam(pname, (String)value,at);
	        } else if(value instanceof Collection){
	        	checkCollectionParam(pname,(Collection<?>)value,at);
	        }else if(value instanceof Number) {
	        	checkNumberParam(pname, ((Number)value).longValue(),at);
	        }
	        
	        String ptype = at.paramType();
	        if(ptype==null) {
	        	ptype = Parameter.TYPE_BODY;
	        }
	        if(Parameter.TYPE_BODY.equalsIgnoreCase(ptype)) {
	        	typeBody = true;
	        }else if(Parameter.TYPE_FORM.equalsIgnoreCase(ptype) && typeBody){
	        	throw new ApiException(String.format("field [%s] type form "
	        			+ "is not compatible with other fields type body", pname)); 
	        }
	       
	    } 
	}
	


	private static void checkNumberParam(String name, long value, Param at) {
        if (at.max() > 0 && value > at.max()) {
            throw new ApiException(String.format("filed [%s] exceeds the max limit [%s]", 
            		name, Long.valueOf(at.maxLength()))); 
        } 
        if (at.min() > 0 && value < at.min()) {
            throw new ApiException(String.format("filed [%s] less than the min limit [%s]", 
            		name, Long.valueOf(at.minLength()))); 
        } 
		
	}

	private static void checkStringParam(String name, String value, Param at) {
		if(value.isEmpty()) {
			if(at.notEmpty()) {
				throw new ApiException(String.format("filed [%s] not allow empty", 
						name)); 
			}else {
				return;
			}
		}
        if (at.maxLength() > 0 && value.length() > at.maxLength()) {
            throw new ApiException(String.format("filed [%s] exceeds the max length [%s]", 
            		name, Integer.valueOf(at.maxLength()))); 
        } 
        if (at.minLength() > 0 && value.length() < at.minLength()) {
            throw new ApiException(String.format("filed [%s] less than the min length [%s]", 
            		name, Integer.valueOf(at.minLength()))); 
        } 
        if (at.validValues()!= null && at.validValues().length > 0)
            validateValue(at.validValues(), value, name);
        if (at.validRegexValue() != null && !at.validRegexValue().trim().equals("")) {
          String regex = at.validRegexValue().trim();
          Pattern pt = Pattern.compile(regex);
          Matcher mt = pt.matcher(value);
          if (!mt.matches())
            throw new ApiException(String.format("field[%s] doesn't match the required regular expression[%s]", 
            		name, regex)); 
        } 
		
	}
	
	private static void checkCollectionParam(String name,Collection<?> value, Param at) {
		if(value.isEmpty() && !at.notEmpty()) {
			throw new ApiException(String.format("filed [%s] not allow empty", 
            		name)); 
		}
        if (at.maxLength() > 0 && value.size() > at.maxLength()) {
            throw new ApiException(String.format("filed [%s] exceeds the max length [%s]", 
            		name, Integer.valueOf(at.maxLength()))); 
        } 
        if (at.minLength() > 0 && value.size() < at.minLength()) {
            throw new ApiException(String.format("filed [%s] less than the min length [%s]", 
            		name, Integer.valueOf(at.minLength()))); 
        } 
		
	}
	
	private static void validateValue(String[] validValues, String value, String fieldName) {
	    Set<String> vals = new HashSet<>();
	    for (String val : validValues)
	      vals.add(val); 
	    if (!vals.contains(value)) {
	    	throw new ApiException(String.format("field [%s], valid values are %s", 
	    			fieldName, String.join(",", vals))); 
	    }
	}
}
