package cn.com.nexwise.sdk.vm.dto;

import java.util.List;

public class QueryVmInstanceResult {
	  public List inventories;
	  
	  public Long total;
	  
	  public void setInventories(List inventories) {
	    this.inventories = inventories;
	  }
	  
	  public List getInventories() {
	    return this.inventories;
	  }
	  
	  public void setTotal(Long total) {
	    this.total = total;
	  }
	  
	  public Long getTotal() {
	    return this.total;
	  }
	}
