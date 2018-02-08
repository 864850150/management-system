package org.yx.common.utils;

/**
 * 
 * <b>类名：</b>ReturnCode.java<br>
 * <p><b>标题：</b>芝麻店</p>
 * <p><b>描述：</b>芝麻店</p>
 * <p><b>版权声明：</b>Copyright (c) 2017</p>
 * <p><b>公司：</b>上海诣新信息科技有限公司 </p>
 * @author <font color='blue'>陈鹏</font> 
 * @version 1.0.1
 * @date  2017年3月6日 上午11:18:18
 * @Description 返回码
 */
public class ReturnCode {
	
	//操作成功
	public static final String SUCCESS="01";

	//参数错误
	public static final String FAIL="20";
	
	//用户名或密码错误
	public static final String LOGIN_FAIL="30";
	
	//非法请求
	public static final String BAD_REQUEST="31";
	
	//非法请求(token为空)
	public static final String TOKEN_NULL="32";
	
	//非法请求(token错误)
	public static final String TOKEN_FAIL="33";
	
	//token超时
	public static final String TOKEN_TIMEOUT="34";
	
	//服务器繁忙
	public static final String SERVICE_FAIL="90";

}
