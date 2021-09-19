package cn.com.nexwise.sdk.vm.dto;

import java.util.Set;

import cn.com.nexwise.sdk.common.OperationTypes;
import cn.com.nexwise.sdk.common.dto.CommonBatchTaskReq;

public class StartVmsReq extends CommonBatchTaskReq {

	public StartVmsReq(Set<String> ids) {
		super(ids, OperationTypes.VM.START,1);
	}
	
}
