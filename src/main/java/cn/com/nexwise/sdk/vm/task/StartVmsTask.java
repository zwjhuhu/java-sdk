package cn.com.nexwise.sdk.vm.task;


import cn.com.nexwise.sdk.common.AsyncTask;
import cn.com.nexwise.sdk.common.RestInfo;
import cn.com.nexwise.sdk.vm.dto.StartVmsReq;

public class StartVmsTask extends AsyncTask {
  
	public StartVmsTask(StartVmsReq	req) {
		super(req);
	}
	  
	protected RestInfo getRestInfo() {
		RestInfo info = new RestInfo();
		info.setHttpMethod("POST");
		info.setPath("/cloudHost/startVM.do");
		return info;
	}

}
