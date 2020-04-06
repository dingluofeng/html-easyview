package com.eason.html.easyview.core.basecontroller;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * </p>
 * 
 * @author DingLuoFeng 2018年3月22日 下午8:31:19
 * @version V1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2018年3月22日
 * @modify by reason:{方法名}:{原因}
 */
public class ResponseResult {

	private static final ResponseResult OK = new ResponseResult();

	public final int status;

	public final String msg;

	public ResponseResult(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public ResponseResult(HttpStatus httpStatus) {
		this(httpStatus.value(), httpStatus.getReasonPhrase());
	}

	public ResponseResult() {
		this(HttpStatus.OK);
	}

	public static ResponseResult ok() {
		return OK;
	}

	public static ResponseResult with(HttpStatus httpStatus) {
		return new ResponseResult(httpStatus);
	}

}
