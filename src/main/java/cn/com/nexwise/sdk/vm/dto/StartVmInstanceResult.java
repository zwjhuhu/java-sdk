package cn.com.nexwise.sdk.vm.dto;

import cn.com.nexwise.sdk.common.dto.VmInstanceInventory;

public class StartVmInstanceResult {
  public VmInstanceInventory inventory;
  
  public void setInventory(VmInstanceInventory inventory) {
    this.inventory = inventory;
  }
  
  public VmInstanceInventory getInventory() {
    return this.inventory;
  }
}

