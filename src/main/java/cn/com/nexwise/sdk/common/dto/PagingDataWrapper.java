package cn.com.nexwise.sdk.common.dto;


import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回数据
 * @author GDJC
 *
 * @param <T>
 */
public class PagingDataWrapper<T> extends DataWrapper {

	/**
	 * 数据总条数
	 */
	private long total;

	/**
	 * 数据
	 */
	private List<T> rows = new ArrayList<>(0);

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}

