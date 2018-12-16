package pers.jim.util;

public enum ApiStatusCode {
	ApiVerifyFailed(412,"错误"),
	ApiServerFailed(417,"服务异常");
	private final int statusCode;
	private final String statusDesc;
	private ApiStatusCode(int statusCode,String statusDesc) {
		this.statusCode = statusCode;
		this.statusDesc = statusDesc;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	
}
