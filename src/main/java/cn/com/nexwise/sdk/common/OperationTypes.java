package cn.com.nexwise.sdk.common;


/**
 * 操作类型定义
 * 
 * @author caizhiping
 * @since 2019年7月29日 上午11:22:37
 */
public final class OperationTypes {

	private OperationTypes() {
	}

	/**
	 * 云主机相关操作类型
	 * 
	 */
	public static class VM {
		private VM() {
		}

		/**
		 * 只是个整数范围标记
		 */
		public static final short LOWER = 1000;

		/**
		 * 创建云主机
		 */
		public static final short CREATE = 1001;

		/**
		 * 启动云主机
		 */
		public static final short START = 1002;

		/**
		 * 停止云主机
		 */
		public static final short STOP = 1003;

		/**
		 * 重启云主机
		 */
		public static final short RESTART = 1004;

		/**
		 * 恢复云主机
		 */
		public static final short RESUME = 1005;

		/**
		 * 暂停云主机
		 */
		public static final short PAUSE = 1006;

		/**
		 * 关闭电源
		 */
		public static final short POWEROFF = 1007;

		/**
		 * 删除云主机
		 */
		public static final short DELETE = 1008;

		/**
		 * 恢复已删除云主机
		 */
		public static final short RESUME_DELETED = 1009;

		/**
		 * 永久删除云主机
		 */
		public static final short REMOVE = 1010;

		/**
		 * 变更云主机所有者
		 */
		public static final short CHANGE_OWNER = 1011;

		/**
		 * 云主机系统扩容
		 */
		public static final short EXPANSION = 1012;

		/**
		 * 创建云主机镜像
		 */
		public static final short CREATE_VM_IMAGE = 1013;

		/**
		 * 加载云盘
		 */
		public static final short LOAD_CLOUD_DISK = 1017;

		/**
		 * 卸载云盘
		 */
		public static final short UNLOAD_CLOUD_DISK = 1018;

		/**
		 * 设置高可用
		 */
		public static final short SET_HA = 1019;

		/**
		 * 修改计算规格
		 */
		public static final short CHANGE_SPEC = 1020;

		/**
		 * 设置启动顺序
		 */
		public static final short SET_BOOT_ORDER = 1021;

		/**
		 * 设置控制台密码setVncPassword
		 */
		public static final short SET_VNC_PASSWORD = 1022;

		/**
		 * 更换操作系统
		 */
		public static final short CHANGE_OS = 1023;

		/**
		 * 创建光驱
		 */
		public static final short CREATE_CDROM = 1024;

		/**
		 * 加载ISO
		 */
		public static final short LOAD_ISO = 1025;

		/**
		 * 卸载ISO
		 */
		public static final short UNLOAD_ISO = 1026;

		/**
		 * 删除光驱
		 */
		public static final short DELETE_CDROM = 1027;

		/**
		 * 重置云主机
		 */
		public static final short RESET_VM = 1028;

		/**
		 * 修改CPU个数
		 */
		public static final short CHANGE_NUMBER_OF_CPU = 1029;

		/**
		 * 修改内存
		 */
		public static final short CHANGE_ARM = 1030;

		/**
		 * 设置云盘Qos
		 */
		public static final short SET_DISK_QOS = 1031;

		/**
		 * 取消云盘Qos
		 */
		public static final short UNSET_DISK_QOS = 1032;

		/**
		 * 取消控制台密码
		 */
		public static final short UN_SET_VNC_PASSWORD = 1033;

		/**
		 * 加载网卡
		 */
		public static final short PLUG_NIC = 1034;

		/**
		 * 卸载网卡
		 */
		public static final short UNPLUG_NIC = 1035;

		/**
		 * 指定IP
		 */
		public static final short SET_IP = 1036;

		/**
		 * 取消指定IP
		 */
		public static final short UNSET_IP = 1037;

		/**
		 * 设置网卡MAC
		 */
		public static final short SET_NIC_MAC = 1038;

		/**
		 * 设置网卡QoS
		 */
		public static final short SET_NIC_QOS = 1039;

		/**
		 * 取消网卡QoS
		 */
		public static final short UNSET_NIC_QOS = 1040;

		/**
		 * 只是个整数范围标记
		 */
		public static final short UPPER = 1099;

	}

	/**
	 * 云盘相关操作类型
	 * 
	 */
	public static class VMDISK {

		private VMDISK() {
		}

		/**
		 * 只是个整数范围标记
		 */
		public static final short LOWER = 1900;

		/**
		 * 创建
		 */
		public static final short CREATE = 1901;

		/**
		 * 修改
		 */
		public static final short UPDATE = 1902;

		/**
		 * 删除
		 */
		public static final short DELETE = 1903;

		/**
		 * 彻底删除
		 */
		public static final short DESTORY = 1911;

		/**
		 * 扩容
		 */
		public static final short RESIZE = 1912;

		/**
		 * 创建云盘快照
		 */
		public static final short CREATE_SNAPSHOT = 1917;

		/**
		 * 删除云盘快照
		 */
		public static final short DELETE_SNAPSHOT = 1918;

		/**
		 * 恢复云盘快照
		 */
		public static final short RESUME_SNAPSHOT = 1919;

		/**
		 * 克隆云盘
		 */
		public static final short CLONE = 1920;

		/**
		 * 加载云盘
		 */
		public static final short LOAD_CLOUD_DISK = 1906;

		/**
		 * 卸载云盘
		 */
		public static final short UNLOAD_CLOUD_DISK = 1907;

		/**
		 * 创建云盘镜像
		 */
		public static final short CREATE_DISK_IMAGE = 1909;

		/**
		 * 设置QoS
		 */
		public static final short SET_QOS = 1914;

		/**
		 * 取消QoS
		 */
		public static final short CANCEL_QOS = 1915;

		/**
		 * 只是个整数范围标记
		 */
		public static final short UPPER = 1999;

	}

	/**
	 * 主存储
	 * 
	 * @author caizhiping
	 * @since 2019年9月20日 下午2:42:12
	 */
	public static class PrimaryStorage {

		private PrimaryStorage() {

		}

		/**
		 * 只是个整数范围标记
		 */
		public static final short LOWER = 1800;

		/**
		 * 添加主存储
		 */
		public static final short CREATE = 1801;

		/**
		 * 删除主存储
		 */
		public static final short DELETE = 1803;

		/**
		 * 停用主存储
		 */
		public static final short STOP = 1804;

		/**
		 * 启用主存储
		 */
		public static final short START = 1805;

		/**
		 * 主存储加载到集群中
		 */
		public static final short LOAD_CLUSTER = 1807;

		/**
		 * 把主存储从集群中卸载
		 */
		public static final short UNLOAD_CLUSTER = 1808;

		/**
		 * 只是个整数范围标记
		 */
		public static final short UPPER = 1899;
	}

	/**
	 * 云路由相关操作
	 * 
	 * @author zwj
	 * @date 2019/10/18
	 */
	public static final class Vrouter {

		private Vrouter() {

		}

		/**
		 * 只是个整数范围标记
		 */
		public static final short LOWER = 5500;

		/**
		 * 启动
		 */
		public static final short START = 5501;

		/**
		 * 重启
		 */
		public static final short RESTART = 5502;

		/**
		 * 重连
		 */
		public static final short RECONN = 5503;

		/**
		 * 迁移
		 */
		public static final short MIGRATE = 5504;
		/**
		 * 打开控制台
		 */
		public static final short OPEN_CONSOLE = 5505;

		/**
		 * 设置控制台密码
		 */
		public static final short SET_CONSOLEPWD = 5506;

		/**
		 * 取消控制台密码
		 */
		public static final short UNSET_CONSOLEPWD = 5507;

		/**
		 * 删除(这里是彻底删除，无法恢复)
		 */
		public static final short REMOVE = 5508;

		/**
		 * 修改CPU个数
		 */
		public static final short CPU_NUMBER = 5509;

		/**
		 * 修改内存大小
		 */
		public static final short RESIZE_RAM = 5510;

		/**
		 * 加载网卡
		 */
		public static final short PLUG_NIC = 5511;

		/**
		 * 卸载网卡
		 */
		public static final short UNPLUG_NIC = 5512;

		/**
		 * 只是个整数范围标记
		 */
		public static final short UPPER = 5599;
	}

	/**
	 * 三层网络相关操作
	 */
	public static final class L3Network {
		private L3Network() {

		}

		/**
		 * 只是个整数范围标记
		 */
		public static final short LOWER = 5201;

		/**
		 * 删除公有网络
		 */
		public static final short DELETE_PUBLIC_NETWORK = 5203;

		/**
		 * 删除公有网络段
		 */
		public static final short DELETE_PUBLIC_RANGE = 5205;

		/**
		 * 公有网络的云主机加载网卡
		 */
		public static final short PLUG_PUBLIC_NIC = 5206;

		/**
		 * 公有网络的云主机卸载网卡
		 */
		public static final short UNPLUG_PUBLIC_NIC = 5207;

		/**
		 * 删除私有网络
		 */
		public static final short DELETE_PRIVATE_NETWORK = 5403;

		/**
		 * 删除私有网络段
		 */
		public static final short DELETE_PRIVATE_RANGE = 5405;

		/**
		 * 私有网络的云主机加载网卡
		 */
		public static final short PLUG_PRIVATE_NIC = 5406;

		/**
		 * 私有网络的云主机卸载网卡
		 */
		public static final short UNPLUG_PRIVATE_NIC = 5407;

		/**
		 * 删除系统网络
		 */
		public static final short DELETE_SYSTEM_NETWORK = 5303;

		/**
		 * 删除系统网络段
		 */
		public static final short DELETE_SYSTEM_RANGE = 5305;

		/**
		 * 只是个整数范围标记
		 */
		public static final short UPPER = 5499;
	}

	/**
	 * 二层网络相关操作
	 */
	public static final class L2Network {
		private L2Network() {

		}

		/**
		 * 只是个整数范围标记
		 */
		public static final short LOWER = 5000;
		
		/**
		 * 创建vxlan pool
		 */
		public static final short CREATE_VXLAN_POOL = 5001;
		
	      /**
         * 修改vxlan pool
         */
        public static final short MODIFY_VXLAN_POOL = 5002;
        
        /**
         * 删除vxlan pool
         */
        public static final short DELETE_VXLAN_POOL = 5003;
        
        /**
         *vxlan pool 加载集群
         */
        public static final short VXLAN_POOL_LOAD_CLUSTER = 5004;
        
        /**
         * vxlan pool 卸载集群
         */
        public static final short VXLAN_POOL_UNLOAD_CLUSTER = 5005;

		/**
		 * 创建二层网络
		 */
		public static final short CREATE_L2_NETWORK = 5101;
		
	      /**
         * 修改二层网络
         */
        public static final short MODIFY_L2_NETWORK = 5102;

		/**
		 * 删除二层网络
		 */
		public static final short DELETE_L2_NETWORK = 5103;

		/**
		 * 加载集群
		 */
		public static final short LOAD_CLUSTER = 5104;

		/**
		 * 删除集群
		 */
		public static final short UNLOAD_CLUSTER = 5105;

		/**
		 * 重新创建二层网络
		 */
		public static final short RECREATE = 5106;
		
	      /**
         * 创建VXLAN二层
         */
        public static final short CREATE_VXLAN_NETWORK = 5107;
        
        /**
         * 删除VXLAN二层
         */
        public static final short DELETE_VXLAN_NETWORK = 5108;

		/**
		 * 只是个整数范围标记
		 */
		public static final short UPPER = 5199;

	}

	/**
	 * 镜像服务器
	 *
	 */
	public static final class MirrorServer {
		private MirrorServer() {

		}

		/**
		 * 只是个整数范围标记
		 */
		public static final short LOWER = 2000;

		/**
		 * 添加镜像服务器
		 */
		public static final short CREATE = 2001;

		/**
		 * 删除镜像服务器
		 */
		public static final short DELETE = 2002;

		/**
		 * 只是个整数范围标记
		 */
		public static final short UPPER = 2199;
	}

	/**
	 * 安全组
	 *
	 */
	public static final class SecurityGroup {
		private SecurityGroup() {

		}

		/**
		 * 只是个整数范围标记
		 */
		public static final short LOWER = 5700;

		/**
		 * 创建安全组
		 */
		public static final short CREATE = 5701;

		/**
		 * 删除安全组
		 */
		public static final short DELETE = 5705;

		/**
		 * 停用安全组
		 */
		public static final short DISABLE = 5703;

		/**
		 * 启用安全组
		 */
		public static final short ENABLE = 5704;

		/**
		 * 加载三层网络
		 */
		public static final short LOAD_L3_NETWORK = 5706;

		/**
		 * 卸载三层网络
		 */
		public static final short UNLOAD_L3_NETWORK = 5707;

		/**
		 * 绑定云主机网卡
		 */
		public static final short BIND_NETWORK_CARD = 5708;

		/**
		 * 解绑云主机网卡
		 */
		public static final short UNBIND_NETWORK_CARD = 5709;

		/**
		 * 添加规则
		 */
		public static final short ADD_RULE = 5710;

		/**
		 * 删除规则
		 */
		public static final short DELETE_RULE = 5711;

		/**
		 * 只是个整数范围标记
		 */
		public static final short UPPER = 5799;
	}
	
	/**
	 * 端口转发
	 *
	 */
	public static final class PortForwarding {
		private PortForwarding() {

		}

		/**
		 * 只是个整数范围标记
		 */
		public static final short LOWER = 8000;

		/**
		 * 创建端口转发
		 */
		public static final short CREATE = 8001;
		
		/**
		 * 绑定云主机
		 */
		public static final short BIND_HOST = 8003;
		
		/**
		 * 解绑云主机网卡
		 */
		public static final short UNBIND_HOST = 8004;
		
		/**
		 * 删除端口转发
		 */
		public static final short DELETE = 8005;


		/**
		 * 只是个整数范围标记
		 */
		public static final short UPPER = 8099;
	}

	/**
	 * 弹性IP
	 *
	 */
	public static final class ElasticIP {
		private ElasticIP() {

		}

		/**
		 * 只是个整数范围标记
		 */
		public static final short LOWER = 5900;

		/**
		 * 创建端口转发
		 */
		public static final short CREATE = 5901;

		/**
		 * 绑定云主机
		 */
		public static final short BIND_HOST = 5903;

		/**
		 * 解绑云主机网卡
		 */
		public static final short UNBIND_HOST = 5904;

		/**
		 * 删除端口转发
		 */
		public static final short DELETE = 5905;


		/**
		 * 只是个整数范围标记
		 */
		public static final short UPPER = 5999;
	}
	
	/**
	 * 虚拟IP
	 *
	 */
	public static final class VirtualIP {
		private VirtualIP() {

		}

		/**
		 * 只是个整数范围标记
		 */
		public static final short LOWER = 5800;

		/**
		 * 创建qos
		 */
		public static final short CREATEQOS = 5802;


		/**
		 * 删除qos
		 */
		public static final short DELETEQOS = 5803;
		
		/**
		 * 删除虚拟IP
		 */
		public static final short DELETE =5804;


		/**
		 * 只是个整数范围标记
		 */
		public static final short UPPER = 5899;
	}
	
	/**
	 * 物理机操作
	 * @author luly
	 *
	 */
	public static final class Host {
		private Host() {

		}
		
		/**
		 * 只是个整数范围标记
		 */
		public static final short LOWER = 1700;

		/**
		 * 创建
		 */
		public static final short CREATE = 1701;


		/**
		 * 删除
		 */
		public static final short DELETE = 1706;
		
		/**
		 * 批量添加
		 */
		public static final short BATCHCREATE=1708;
		
		/**
		 * 只是个整数范围标记
		 */
		public static final short UPPER = 1799;
	}

}

