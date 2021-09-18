package cn.com.nexwise.sdk.vm.dto;


import java.util.List;

import cn.com.nexwise.sdk.common.BytesConver;

public class QueryVmInfo extends ClVmInf {

	/**
	 * 云主机所有者
	 */
	private String owner;

	private String ownertype;

	/**
	 * 镜像
	 */
	private String mname;

	private String biosmodel;

	private String mformat;
	
	private String projectname;

	/**
	 * 区域
	 */
	private String domainname;

	/**
	 * 集群
	 */
	private String clustername;

	/**
	 * 云盘规格
	 */
	private String diskspecname;

	/**
	 * 计算规格
	 */
	private String comspecname;

	/**
	 * 物理机
	 */
	private String pmname;

	private String hostip;

	private String oldpmname;

	private String oldhostip;

	/**
	 * 默认IP
	 */
	private String defaultIp;

	/**
	 * 当前网络使用IP
	 */
	private String currentNetworkUsedIp;

	/**
	 * 网卡
	 */
	private List<ClVmnicInf> vmnicInfList;

	private String memorysizeStr;
	
	private boolean unloadDisk;
	
	/**
	 * 标签信息
	 */
	private List<ClLabelInf> lableName;
	
	/**
	 * 是否正在进行备份操作
	 * 
	 */
	private boolean inBackup;

	private boolean createdBackup;
	
	private byte rootVolumeMsType;

	public String getMemorysizeStr() {
		Long v = super.getMemorysize();
		return v!=null ? BytesConver.formatString((double)v) : "0";
	}

	public void setMemorysizeStr(String memorysizeStr) {
		this.memorysizeStr = memorysizeStr;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<ClVmnicInf> getVmnicInfList() {
		return vmnicInfList;
	}

	public void setVmnicInfList(List<ClVmnicInf> vmnicInfList) {
		this.vmnicInfList = vmnicInfList;
	}

	public String getCurrentNetworkUsedIp() {
		return currentNetworkUsedIp;
	}

	public void setCurrentNetworkUsedIp(String currentNetworkUsedIp) {
		this.currentNetworkUsedIp = currentNetworkUsedIp;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getBiosmodel() {
		return biosmodel;
	}

	public void setBiosmodel(String biosmodel) {
		this.biosmodel = biosmodel;
	}

	public String getDomainname() {
		return domainname;
	}

	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}

	public String getClustername() {
		return clustername;
	}

	public void setClustername(String clustername) {
		this.clustername = clustername;
	}

	public String getDiskspecname() {
		return diskspecname;
	}

	public void setDiskspecname(String diskspecname) {
		this.diskspecname = diskspecname;
	}

	public String getComspecname() {
		return comspecname;
	}

	public void setComspecname(String comspecname) {
		this.comspecname = comspecname;
	}

	public String getPmname() {
		return pmname;
	}

	public void setPmname(String pmname) {
		this.pmname = pmname;
	}

	public String getHostip() {
		return hostip;
	}

	public void setHostip(String hostip) {
		this.hostip = hostip;
	}

	public String getOldpmname() {
		return oldpmname;
	}

	public void setOldpmname(String oldpmname) {
		this.oldpmname = oldpmname;
	}

	public String getOldhostip() {
		return oldhostip;
	}

	public void setOldhostip(String oldhostip) {
		this.oldhostip = oldhostip;
	}

	public String getMformat() {
		return mformat;
	}

	public void setMformat(String mformat) {
		this.mformat = mformat;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getDefaultIp() {
		return defaultIp;
	}

	public void setDefaultIp(String defaultIp) {
		this.defaultIp = defaultIp;
	}

	public boolean isUnloadDisk() {
		return unloadDisk;
	}

	public void setUnloadDisk(boolean unloadDisk) {
		this.unloadDisk = unloadDisk;
	}

	public List<ClLabelInf> getLableName() {
		return lableName;
	}

	public void setLableName(List<ClLabelInf> lableName) {
		this.lableName = lableName;
	}

    public boolean isInBackup() {
        return inBackup;
    }

    public void setInBackup(boolean inBackup) {
        this.inBackup = inBackup;
    }

    public byte getRootVolumeMsType() {
        return rootVolumeMsType;
    }

    public void setRootVolumeMsType(byte rootVolumeMsType) {
        this.rootVolumeMsType = rootVolumeMsType;
    }

    public boolean isCreatedBackup() {
        return createdBackup;
    }

    public void setCreatedBackup(boolean createdBackup) {
        this.createdBackup = createdBackup;
    }

}
