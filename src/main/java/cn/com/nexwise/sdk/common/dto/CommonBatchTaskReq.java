package cn.com.nexwise.sdk.common.dto;

import java.util.Set;

import cn.com.nexwise.sdk.common.Param;
import cn.com.nexwise.sdk.common.Parameter;

public class CommonBatchTaskReq extends BaseOperationTaskReq {
	
	public CommonBatchTaskReq(Set<String> ids,short operationType,int async,String name) {
		this.ids = String.join(",", ids);
		this.setOperationType(operationType);
		LogAction logAction = new LogAction();
		logAction.setAsync(async);
		logAction.setId(operationType);
		logAction.setName(name);
		this.setLogAction(logAction);
	}

	public CommonBatchTaskReq(Set<String> ids, short operationType,int async) {
		this(ids, operationType,async,"");
	}

	@Param(paramType = Parameter.TYPE_BODY, required = true, notEmpty = true)
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
