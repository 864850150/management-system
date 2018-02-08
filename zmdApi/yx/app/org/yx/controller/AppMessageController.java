package org.yx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yx.common.base.BaseController;
import org.yx.common.entity.PageData;
import org.yx.common.utils.AppUtil;
import org.yx.common.utils.MobileMessageCheck;
import org.yx.common.utils.MobileMessageSend;


@Controller
@RequestMapping(value="/appMessage")
public class AppMessageController extends BaseController {  

	    /**
	     * 发送短信验证码
	     * @return
	     */
		@RequestMapping(value="/sendMsg")
		@ResponseBody
		public PageData sendMsg(){
			PageData pd=new PageData();
			pd=getPageData();
			PageData _result=AppUtil.success();
			try {
				String co = MobileMessageSend.sendMsg(pd.getString("PHONE"));
				if(co.equals("success")){
					_result.put("reason", "success");
				}else{
					_result.put("reason", "error");
				}
			} catch (Exception e) {
				e.printStackTrace();
				_result = AppUtil.otherError();
			}
			
			return _result;
		}
		
		/**
	     * 验证短信验证码
	     * @return
	     */
		@RequestMapping(value="/checkMsg")
		@ResponseBody
		public PageData checkMsg(){
			PageData pd=new PageData();
			pd=getPageData();
			PageData _result=AppUtil.success();
			try {
				String co = MobileMessageCheck.checkMsg(pd.getString("PHONE"),pd.getString("CODE"));
				if(co.equals("success")){
					_result.put("reason", "success");
				}else{
					_result.put("reason", "error");
				}
			} catch (Exception e) {
				e.printStackTrace();
				_result = AppUtil.otherError();
			}
			
			return _result;
		}
	} 