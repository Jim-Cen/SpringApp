package pers.jim.app.exception;


public class HttpException extends Exception {
	private static final long serialVersionUID = 1L;
	public int httpStatus;
	/**
	 * @param {int} httpStatus - response返回编号
	 */
	public HttpException(int httpStatus,String message){
		super(message);
		this.httpStatus = httpStatus;
	}
}
