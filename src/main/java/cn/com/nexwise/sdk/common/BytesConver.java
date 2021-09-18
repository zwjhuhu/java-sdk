package cn.com.nexwise.sdk.common;

import java.text.DecimalFormat;

public class BytesConver {
	
	private static final double SPACE_KB = 1024.0;
	private static final double SPACE_MB = 1024.0 * SPACE_KB;
	private static final double SPACE_GB = 1024.0 * SPACE_MB;
	private static final double SPACE_TB = 1024.0 * SPACE_GB;

	public static long kb2Bytes(long kb) {
		return (long) (kb * SPACE_KB);
	}

	public static long mb2Bytes(long mb) {
		return (long) (mb * SPACE_MB);
	}

	public static long gb2Bytes(long gb) {
		return (long) (gb * SPACE_GB);
	}

	public static long tb2Bytes(long tb) {
		return (long) (tb * SPACE_TB);
	}

	public static String formatString(double size) {
		String sizeStr = null;

		double b = size;
		double k = size / SPACE_KB;
		double m = size / SPACE_MB;
		double g = size / SPACE_GB;
		double t = size / SPACE_TB;
		DecimalFormat dec = new DecimalFormat("0.00");

		if (t >= 1) {
			sizeStr = dec.format(t).concat(" TB");
		} else if (g >= 1) {
			sizeStr = dec.format(g).concat(" GB");
		} else if (m >= 1) {
			sizeStr = dec.format(m).concat(" MB");
		} else if (k >= 1) {
			sizeStr = dec.format(k).concat(" KB");
		} else {
			sizeStr = dec.format(b).concat(" Bytes");
		}

		return sizeStr;
	}
	
	public static String formatString2(double size) {
		String sizeStr = null;

		double b = size;
		double k = size / SPACE_KB;
		double m = size / SPACE_MB;
		double g = size / SPACE_GB;
		double t = size / SPACE_TB;
		DecimalFormat dec = new DecimalFormat("0");

		if (t >= 1) {
			sizeStr = dec.format(t).concat(" TB");
		} else if (g >= 1) {
			sizeStr = dec.format(g).concat(" GB");
		} else if (m >= 1) {
			sizeStr = dec.format(m).concat(" MB");
		} else if (k >= 1) {
			sizeStr = dec.format(k).concat(" K");
		} else {
			sizeStr = dec.format(b).concat(" B");
		}

		return sizeStr;
	}
	
	public static String formatGB(double size) {
		String sizeStr = null;

		double b = size;
		double k = size / SPACE_KB;
		double m = size / SPACE_MB;
		double g = size / SPACE_GB;
		DecimalFormat dec = new DecimalFormat("0.00");

		if (g >= 1) {
			sizeStr = dec.format(g).concat(" GB");
		} else if (m >= 1) {
			sizeStr = dec.format(m).concat(" MB");
		} else if (k >= 1) {
			sizeStr = dec.format(k).concat(" KB");
		} else {
			sizeStr = dec.format(b).concat(" Bytes");
		}

		return sizeStr;
	}

	public static SizeBean formatBean(double size) {
		SizeBean sizeBean = new SizeBean();

		double b = size;
		double k = size / SPACE_KB;
		double m = size / SPACE_MB;
		double g = size / SPACE_GB;
		double t = size / SPACE_TB;

		if (t >= 1) {
			sizeBean.setSize(t);
			sizeBean.setUnit("TB");
		} else if (g >= 1) {
			sizeBean.setSize(g);
			sizeBean.setUnit("GB");
		} else if (m >= 1) {
			sizeBean.setSize(m);
			sizeBean.setUnit("MB");
		} else if (k >= 1) {
			sizeBean.setSize(k);
			sizeBean.setUnit("KB");
		} else {
			sizeBean.setSize(b);
			sizeBean.setUnit("Bytes");
		}

		return sizeBean;
	}

	public static class SizeBean {
		private double size;
		private String unit;

		public double getSize() {
			return size;
		}

		public void setSize(double size) {
			this.size = size;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

	}
	
	public static long getBytes(String unit, long size) {
		long bytes = 0;
		switch (unit) {
		case "KB":
			bytes = BytesConver.kb2Bytes(size);
			break;
		case "MB":
			bytes = BytesConver.mb2Bytes(size);
			break;
		case "GB":
			bytes = BytesConver.gb2Bytes(size);
			break;
		case "TB":
			bytes = BytesConver.tb2Bytes(size);
			break;
		default:
			break;
		}

		return bytes;
	}
}
