package cn.com.nexwise.sdk.vm.dto;


import java.util.Date;

public class ClVmnicInf {
    
    private String vmnicid;

    private String vmid;

    private String nid;

    private String ip;

    private String mac;

    private String gateway;

    private String netmask;

    private String vmnicname;

    private String hypevisortype;

    private Integer ipversion;

    private String creater;

    private Date creationtime;

    private String modifier;

    private Date modificationtime;

    private Long outboundbandwidth;

    private Long inboundbandwidth;

    private String iprangeid;

    private String usedipid;

    private Byte nictype;
    

    private Byte kvmvftype;

    public Byte getKvmvftype() {
		return kvmvftype;
	}

	public void setKvmvftype(Byte kvmvftype) {
		this.kvmvftype = kvmvftype;
	}

	public String getVmnicid() {
        return vmnicid;
    }

    public void setVmnicid(String vmnicid) {
        this.vmnicid = vmnicid == null ? null : vmnicid.trim();
    }

    public String getVmid() {
        return vmid;
    }

    public void setVmid(String vmid) {
        this.vmid = vmid == null ? null : vmid.trim();
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid == null ? null : nid.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway == null ? null : gateway.trim();
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask == null ? null : netmask.trim();
    }

    public String getVmnicname() {
        return vmnicname;
    }

    public void setVmnicname(String vmnicname) {
        this.vmnicname = vmnicname == null ? null : vmnicname.trim();
    }

    public String getHypevisortype() {
        return hypevisortype;
    }

    public void setHypevisortype(String hypevisortype) {
        this.hypevisortype = hypevisortype == null ? null : hypevisortype.trim();
    }

    public Integer getIpversion() {
        return ipversion;
    }

    public void setIpversion(Integer ipversion) {
        this.ipversion = ipversion;
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

    public Long getOutboundbandwidth() {
        return outboundbandwidth;
    }

    public void setOutboundbandwidth(Long outboundbandwidth) {
        this.outboundbandwidth = outboundbandwidth;
    }

    public Long getInboundbandwidth() {
        return inboundbandwidth;
    }

    public void setInboundbandwidth(Long inboundbandwidth) {
        this.inboundbandwidth = inboundbandwidth;
    }

    public String getIprangeid() {
        return iprangeid;
    }

    public void setIprangeid(String iprangeid) {
        this.iprangeid = iprangeid == null ? null : iprangeid.trim();
    }

    public String getUsedipid() {
        return usedipid;
    }

    public void setUsedipid(String usedipid) {
        this.usedipid = usedipid == null ? null : usedipid.trim();
    }

    public Byte getNictype() {
        return nictype;
    }

    public void setNictype(Byte nictype) {
        this.nictype = nictype;
    }

	@Override
	public String toString() {
		return "ClVmnicInf [vmnicid=" + vmnicid + ", vmid=" + vmid + ", nid=" + nid + ", ip=" + ip + ", mac=" + mac
				+ ", gateway=" + gateway + ", netmask=" + netmask + ", vmnicname=" + vmnicname + ", hypevisortype="
				+ hypevisortype + ", ipversion=" + ipversion + ", creater=" + creater + ", creationtime=" + creationtime
				+ ", modifier=" + modifier + ", modificationtime=" + modificationtime + ", outboundbandwidth="
				+ outboundbandwidth + ", inboundbandwidth=" + inboundbandwidth + ", iprangeid=" + iprangeid
				+ ", usedipid=" + usedipid + ", nictype=" + nictype + "]";
	}
    
}