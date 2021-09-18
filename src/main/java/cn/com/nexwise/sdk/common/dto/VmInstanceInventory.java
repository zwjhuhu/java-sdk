package cn.com.nexwise.sdk.common.dto;


import java.sql.Timestamp;
import java.util.List;

public class VmInstanceInventory {
  public String uuid;
  
  public String name;
  
  public String description;
  
  public String zoneUuid;
  
  public String clusterUuid;
  
  public String imageUuid;
  
  public String hostUuid;
  
  public String lastHostUuid;
  
  public String instanceOfferingUuid;
  
  public String rootVolumeUuid;
  
  public String platform;
  
  public String defaultL3NetworkUuid;
  
  public String type;
  
  public String hypervisorType;
  
  public Long memorySize;
  
  public Integer cpuNum;
  
  public Long cpuSpeed;
  
  public String allocatorStrategy;
  
  public Timestamp createDate;
  
  public Timestamp lastOpDate;
  
  public String state;
  
  public List vmNics;
  
  public List allVolumes;
  
  public List vmCdRoms;
  
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setZoneUuid(String zoneUuid) {
    this.zoneUuid = zoneUuid;
  }
  
  public String getZoneUuid() {
    return this.zoneUuid;
  }
  
  public void setClusterUuid(String clusterUuid) {
    this.clusterUuid = clusterUuid;
  }
  
  public String getClusterUuid() {
    return this.clusterUuid;
  }
  
  public void setImageUuid(String imageUuid) {
    this.imageUuid = imageUuid;
  }
  
  public String getImageUuid() {
    return this.imageUuid;
  }
  
  public void setHostUuid(String hostUuid) {
    this.hostUuid = hostUuid;
  }
  
  public String getHostUuid() {
    return this.hostUuid;
  }
  
  public void setLastHostUuid(String lastHostUuid) {
    this.lastHostUuid = lastHostUuid;
  }
  
  public String getLastHostUuid() {
    return this.lastHostUuid;
  }
  
  public void setInstanceOfferingUuid(String instanceOfferingUuid) {
    this.instanceOfferingUuid = instanceOfferingUuid;
  }
  
  public String getInstanceOfferingUuid() {
    return this.instanceOfferingUuid;
  }
  
  public void setRootVolumeUuid(String rootVolumeUuid) {
    this.rootVolumeUuid = rootVolumeUuid;
  }
  
  public String getRootVolumeUuid() {
    return this.rootVolumeUuid;
  }
  
  public void setPlatform(String platform) {
    this.platform = platform;
  }
  
  public String getPlatform() {
    return this.platform;
  }
  
  public void setDefaultL3NetworkUuid(String defaultL3NetworkUuid) {
    this.defaultL3NetworkUuid = defaultL3NetworkUuid;
  }
  
  public String getDefaultL3NetworkUuid() {
    return this.defaultL3NetworkUuid;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public String getType() {
    return this.type;
  }
  
  public void setHypervisorType(String hypervisorType) {
    this.hypervisorType = hypervisorType;
  }
  
  public String getHypervisorType() {
    return this.hypervisorType;
  }
  
  public void setMemorySize(Long memorySize) {
    this.memorySize = memorySize;
  }
  
  public Long getMemorySize() {
    return this.memorySize;
  }
  
  public void setCpuNum(Integer cpuNum) {
    this.cpuNum = cpuNum;
  }
  
  public Integer getCpuNum() {
    return this.cpuNum;
  }
  
  public void setCpuSpeed(Long cpuSpeed) {
    this.cpuSpeed = cpuSpeed;
  }
  
  public Long getCpuSpeed() {
    return this.cpuSpeed;
  }
  
  public void setAllocatorStrategy(String allocatorStrategy) {
    this.allocatorStrategy = allocatorStrategy;
  }
  
  public String getAllocatorStrategy() {
    return this.allocatorStrategy;
  }
  
  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }
  
  public Timestamp getCreateDate() {
    return this.createDate;
  }
  
  public void setLastOpDate(Timestamp lastOpDate) {
    this.lastOpDate = lastOpDate;
  }
  
  public Timestamp getLastOpDate() {
    return this.lastOpDate;
  }
  
  public void setState(String state) {
    this.state = state;
  }
  
  public String getState() {
    return this.state;
  }
  
  public void setVmNics(List vmNics) {
    this.vmNics = vmNics;
  }
  
  public List getVmNics() {
    return this.vmNics;
  }
  
  public void setAllVolumes(List allVolumes) {
    this.allVolumes = allVolumes;
  }
  
  public List getAllVolumes() {
    return this.allVolumes;
  }
  
  public void setVmCdRoms(List vmCdRoms) {
    this.vmCdRoms = vmCdRoms;
  }
  
  public List getVmCdRoms() {
    return this.vmCdRoms;
  }
}
