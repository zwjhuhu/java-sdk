package cn.com.nexwise.sdk.vm.dto;


import java.util.Date;

public class ClVmInf {

	private String vmid;

	private String mirrorid;

	private String domainid;

	private String clusterid;

	private String rootvolumeid;

	private String specid;

	private String defaultl3networkid;

	private Integer cpunum;

	private Long cpuspeed;

	private Long memorysize;

	private Byte allocatorstrategy;

	private String name;

	private String remark;

	private String type;

	private String state;

	private String platform;

	private String creater;

	private Date creationtime;

	private String modifier;

	private Date modificationtime;

	private String hostid;

	private String oldhostid;

	private String consolepassword;

	private String sshkey;

	private String userdata;

	private String vmcpupining;

	private Byte vmconsolemode;
	
	private Byte nextconsolemode;

	private Boolean usbredirect;

	private Byte allocatorstrategy2;

	private Integer maxvmcount;

	private Byte strategymodel;

	private String projectid;

	private String agid;

	private Integer consoleport;

	private Boolean deleted;

	private Byte bootorder;

	private Byte vmlevel;

	private Long maxmemorysize;

	private Byte hostlevel;

	private Boolean migrate;

	private String autoscalinggroupid;

	private Byte ownertype;

	private Boolean rdpmode;
	
	private Byte servicetype;

	public String getVmid() {
		return vmid;
	}

	public void setVmid(String vmid) {
		this.vmid = vmid == null ? null : vmid.trim();
	}

	public String getMirrorid() {
		return mirrorid;
	}

	public void setMirrorid(String mirrorid) {
		this.mirrorid = mirrorid == null ? null : mirrorid.trim();
	}

	public String getDomainid() {
		return domainid;
	}

	public void setDomainid(String domainid) {
		this.domainid = domainid == null ? null : domainid.trim();
	}

	public String getClusterid() {
		return clusterid;
	}

	public void setClusterid(String clusterid) {
		this.clusterid = clusterid == null ? null : clusterid.trim();
	}

	public String getRootvolumeid() {
		return rootvolumeid;
	}

	public void setRootvolumeid(String rootvolumeid) {
		this.rootvolumeid = rootvolumeid == null ? null : rootvolumeid.trim();
	}

	public String getSpecid() {
		return specid;
	}

	public void setSpecid(String specid) {
		this.specid = specid == null ? null : specid.trim();
	}

	public String getDefaultl3networkid() {
		return defaultl3networkid;
	}

	public void setDefaultl3networkid(String defaultl3networkid) {
		this.defaultl3networkid = defaultl3networkid == null ? null : defaultl3networkid.trim();
	}

	public Integer getCpunum() {
		return cpunum;
	}

	public void setCpunum(Integer cpunum) {
		this.cpunum = cpunum;
	}

	public Long getCpuspeed() {
		return cpuspeed;
	}

	public void setCpuspeed(Long cpuspeed) {
		this.cpuspeed = cpuspeed;
	}

	public Long getMemorysize() {
		return memorysize;
	}

	public void setMemorysize(Long memorysize) {
		this.memorysize = memorysize;
	}

	public Byte getAllocatorstrategy() {
		return allocatorstrategy;
	}

	public void setAllocatorstrategy(Byte allocatorstrategy) {
		this.allocatorstrategy = allocatorstrategy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform == null ? null : platform.trim();
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater == null ? null : creater.trim();
	}

	public Date getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier == null ? null : modifier.trim();
	}

	public Date getModificationtime() {
		return modificationtime;
	}

	public void setModificationtime(Date modificationtime) {
		this.modificationtime = modificationtime;
	}

	public String getHostid() {
		return hostid;
	}

	public void setHostid(String hostid) {
		this.hostid = hostid == null ? null : hostid.trim();
	}

	public String getOldhostid() {
		return oldhostid;
	}

	public void setOldhostid(String oldhostid) {
		this.oldhostid = oldhostid == null ? null : oldhostid.trim();
	}

	public String getConsolepassword() {
		return consolepassword;
	}

	public void setConsolepassword(String consolepassword) {
		this.consolepassword = consolepassword == null ? null : consolepassword.trim();
	}

	public String getSshkey() {
		return sshkey;
	}

	public void setSshkey(String sshkey) {
		this.sshkey = sshkey == null ? null : sshkey.trim();
	}

	public String getUserdata() {
		return userdata;
	}

	public void setUserdata(String userdata) {
		this.userdata = userdata == null ? null : userdata.trim();
	}

	public String getVmcpupining() {
		return vmcpupining;
	}

	public void setVmcpupining(String vmcpupining) {
		this.vmcpupining = vmcpupining == null ? null : vmcpupining.trim();
	}

	public Byte getVmconsolemode() {
		return vmconsolemode;
	}

	public void setVmconsolemode(Byte vmconsolemode) {
		this.vmconsolemode = vmconsolemode;
	}

	public Boolean getUsbredirect() {
		return usbredirect;
	}

	public void setUsbredirect(Boolean usbredirect) {
		this.usbredirect = usbredirect;
	}

	public Byte getAllocatorstrategy2() {
		return allocatorstrategy2;
	}

	public void setAllocatorstrategy2(Byte allocatorstrategy2) {
		this.allocatorstrategy2 = allocatorstrategy2;
	}

	public Integer getMaxvmcount() {
		return maxvmcount;
	}

	public void setMaxvmcount(Integer maxvmcount) {
		this.maxvmcount = maxvmcount;
	}

	public Byte getStrategymodel() {
		return strategymodel;
	}

	public void setStrategymodel(Byte strategymodel) {
		this.strategymodel = strategymodel;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid == null ? null : projectid.trim();
	}

	public String getAgid() {
		return agid;
	}

	public void setAgid(String agid) {
		this.agid = agid == null ? null : agid.trim();
	}

	public Integer getConsoleport() {
		return consoleport;
	}

	public void setConsoleport(Integer consoleport) {
		this.consoleport = consoleport;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Byte getBootorder() {
		return bootorder;
	}

	public void setBootorder(Byte bootorder) {
		this.bootorder = bootorder;
	}

	public Byte getVmlevel() {
		return vmlevel;
	}

	public void setVmlevel(Byte vmlevel) {
		this.vmlevel = vmlevel;
	}

	public Long getMaxmemorysize() {
		return maxmemorysize;
	}

	public void setMaxmemorysize(Long maxmemorysize) {
		this.maxmemorysize = maxmemorysize;
	}

	public Byte getHostlevel() {
		return hostlevel;
	}

	public void setHostlevel(Byte hostlevel) {
		this.hostlevel = hostlevel;
	}

	public Boolean getMigrate() {
		return migrate;
	}

	public void setMigrate(Boolean migrate) {
		this.migrate = migrate;
	}

	public String getAutoscalinggroupid() {
		return autoscalinggroupid;
	}

	public void setAutoscalinggroupid(String autoscalinggroupid) {
		this.autoscalinggroupid = autoscalinggroupid == null ? null : autoscalinggroupid.trim();
	}

	public Byte getOwnertype() {
		return ownertype;
	}

	public void setOwnertype(Byte ownertype) {
		this.ownertype = ownertype;
	}

	public Boolean getRdpmode() {
		return rdpmode;
	}

	public void setRdpmode(Boolean rdpmode) {
		this.rdpmode = rdpmode;
	}

	public Byte getServicetype() {
		return servicetype;
	}

	public void setServicetype(Byte servicetype) {
		this.servicetype = servicetype;
	}

	public Byte getNextconsolemode() {
		return nextconsolemode;
	}

	public void setNextconsolemode(Byte nextconsolemode) {
		this.nextconsolemode = nextconsolemode;
	}
}
