package cn.com.nexwise.sdk.common.dto;

import java.util.LinkedHashMap;

public class ErrorCode {
  public String code;
  
  public String description;
  
  public String details;
  
  public String elaboration;
  
  public String location;
  
  public String cost;
  
  public ErrorCode cause;
  
  public LinkedHashMap opaque;
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getCode() {
    return this.code;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setDetails(String details) {
    this.details = details;
  }
  
  public String getDetails() {
    return this.details;
  }
  
  public void setElaboration(String elaboration) {
    this.elaboration = elaboration;
  }
  
  public String getElaboration() {
    return this.elaboration;
  }
  
  public void setLocation(String location) {
    this.location = location;
  }
  
  public String getLocation() {
    return this.location;
  }
  
  public void setCost(String cost) {
    this.cost = cost;
  }
  
  public String getCost() {
    return this.cost;
  }
  
  public void setCause(ErrorCode cause) {
    this.cause = cause;
  }
  
  public ErrorCode getCause() {
    return this.cause;
  }
  
  public void setOpaque(LinkedHashMap opaque) {
    this.opaque = opaque;
  }
  
  public LinkedHashMap getOpaque() {
    return this.opaque;
  }
}

