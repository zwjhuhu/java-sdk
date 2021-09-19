package cn.com.nexwise.sdk.common.dto;


import java.util.Date;
import java.util.List;

public class TaskResult extends ApiResult {
	/**
	 * 任务id
	 */
	protected String id;

	/**
	 * 用于同步操作保存审计日志，资源id可能有多个，例如删除多条记录等
	 */
	protected List<String> resourceIds;

	/**
	  1：操作成功
	  2：处理中
	  3：操作超时
	  0/-1：操作失败
	*/
	protected int actionResult;

	/**
	 * 返回结果
	 */
	protected String returnResult;
	
	/**
	 * 创建时间
	 */
	protected Date createTime;

	/**
	 * 结束时间
	 */
	protected Date endTime;

	/**
	 * 操作类型
	 */
	protected int opType;

	/**
	 * 操作耗时,单位为秒
	 */
	protected String usedTime;


	/**
	 * 客户端ip
	 */
	protected String clientIp;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public List<String> getResourceIds() {
		return resourceIds;
	}


	public void setResourceIds(List<String> resourceIds) {
		this.resourceIds = resourceIds;
	}


	public int getActionResult() {
		return actionResult;
	}


	public void setActionResult(int actionResult) {
		this.actionResult = actionResult;
	}

	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public int getOpType() {
		return opType;
	}


	public void setOpType(int opType) {
		this.opType = opType;
	}


	public String getUsedTime() {
		return usedTime;
	}


	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}


	public String getClientIp() {
		return clientIp;
	}


	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}
	
	public boolean checkCompleted() {
		return checkSuccess() && actionResult != 2;
	}
	
}

