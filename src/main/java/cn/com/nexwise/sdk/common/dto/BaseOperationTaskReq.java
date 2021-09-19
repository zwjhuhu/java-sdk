package cn.com.nexwise.sdk.common.dto;

import cn.com.nexwise.sdk.common.Param;
import cn.com.nexwise.sdk.common.Parameter;

public class BaseOperationTaskReq extends ApiRequest {

	@Param(paramType = Parameter.TYPE_BODY,required = true)
	private short operationType;
	
	@Param(paramType = Parameter.TYPE_BODY,required = true)
	private LogAction logAction;

	public short getOperationType() {
		return operationType;
	}

	public void setOperationType(short operationType) {
		this.operationType = operationType;
	}

	public LogAction getLogAction() {
		return logAction;
	}

	public void setLogAction(LogAction logAction) {
		this.logAction = logAction;
	}
	
}
