package cn.com.nexwise.sdk.demo;

import cn.com.nexwise.sdk.common.ApiClient;
import cn.com.nexwise.sdk.common.NWXConfig;
import cn.com.nexwise.sdk.common.dto.AccessTokenResult;
import cn.com.nexwise.sdk.common.dto.ApiRequest;
import cn.com.nexwise.sdk.common.dto.PagingDataWrapper;
import cn.com.nexwise.sdk.common.task.AccessTokenTask;
import cn.com.nexwise.sdk.vm.dto.QueryVmInfo;
import cn.com.nexwise.sdk.vm.dto.QueryVmsReq;
import cn.com.nexwise.sdk.vm.task.QueryVmsTask;

/**
 * 演示如何通过sdk调用查询云主机列表信息
 *
 */
public class QueryVmDemo {
	
	public static void main(String[] args) {
		
		 String hostname = "请输入管理节点访问地址";
		 int port = 38080; //请输入管理节点访问端口;
        
		 // sdk基础配置
		 ApiClient.configure(
				new NWXConfig.Builder()
					// .setShowLog(true) 可以配置是否输出调试日志
					.setHostname(hostname)
		            .setPort(port)
		            .build()
		 );
		 
		 // 获取access token
		 String accessKeyId = "请输入系统分配的access key id";
		 String accessKeySecret = "请输入系统分配的access key secret";
		 String accessToken = "请求返回的access token";// token 需要至少每隔1小时重新获取一次
		 ApiRequest tokenReq = new ApiRequest();
		 tokenReq.setAccessKeyId(accessKeyId);
		 tokenReq.setAccessKeySecret(accessKeySecret);
		    
		 AccessTokenResult tokenRes = new AccessTokenTask(tokenReq).query();
		 if(tokenRes.checkSuccess()) {
			 accessToken =  tokenRes.getAccessToken();
		 }
		 
		 // 使用access token 访问API接口
		 
		 QueryVmsReq req = new QueryVmsReq();
		 req.setAccessToken(accessToken);
		 req.setAccessKeySecret(accessKeySecret);
		 req.setLimit(1);
		 QueryVmsTask task = new QueryVmsTask(req);
		 PagingDataWrapper<QueryVmInfo> res = task.query();
		 
		 // 使用返回结果处理...
	}

}
