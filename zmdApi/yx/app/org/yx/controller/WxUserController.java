package org.yx.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yx.common.base.BaseController;
import org.yx.common.entity.PageData;
import org.yx.common.utils.AppUtil;
import org.yx.services.WxProductService;
import org.yx.services.WxShopService;
import org.yx.services.WxUserService;

/**
 * 
 * <b>类名：</b>ApiLoginController.java<br>
 * <p><b>标题：</b>芝麻店微信接口</p>
 * <p><b>描述：</b>芝麻店微信接口</p>
 * <p><b>版权声明：</b>Copyright (c) 2017</p>
 * <p><b>公司：</b>上海诣新信息科技有限公司 </p>
 * @author <font color='blue'>zandezhang</font> 
 * @Description 微信用户相关信息
 */
@Controller
@RequestMapping(value="/wxUser")
public class WxUserController extends BaseController{

	@Resource(name="wxUserService")
	private WxUserService wxUserService;
	/**
	 * 查询用户默认收货地址信息
	 * @return
	 */
	@RequestMapping(value="/findDefalutAddress")
	@ResponseBody
	public PageData findDefalutAddress(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			PageData defaultAddress=wxUserService.findUserDefaultAddress(pd); 
			_result.put("data", defaultAddress);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/*
	 * 查询用户收货地址列表
	 */
	@RequestMapping(value="/findAddressList")
	@ResponseBody
	public PageData findAddressList(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			List<PageData> addressList=wxUserService.findUserAddressList(pd); 
			_result.put("data", addressList);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	/**
	 * 新增用户收货地址
	 * @return
	 */
	@RequestMapping(value="/saveAddress")
	@ResponseBody
	public PageData saveAddress(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			Object ob=wxUserService.saveAddress(pd); 
			_result.put("data", ob);
			if(ob.toString().length() <= 0){
				_result = AppUtil.operateError();
			}
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	/**
	 * 编辑用户收货地址
	 * @return
	 */
	@RequestMapping(value="/editAddress")
	@ResponseBody
	public PageData editAddress(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			Object ob=wxUserService.editAddress(pd); 
			if(Integer.valueOf(ob.toString()) <= 0){
				_result = AppUtil.operateError();
			}
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/**
	 * 设置默认收货地址
	 * @return
	 */
	@RequestMapping(value="/setDefaultAddress")
	@ResponseBody
	public PageData setDefaultAddress(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			Object ob=wxUserService.editIsdefault(pd); 
			if(Integer.valueOf(ob.toString()) <= 0){
				_result = AppUtil.operateError();
			}
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/**
	 * 删除用户收货地址
	 * @return
	 */
	@RequestMapping(value="/delAddress")
	@ResponseBody
	public PageData delAddress(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			Object ob=wxUserService.delAddress(pd); 
			if(Integer.valueOf(ob.toString()) <= 0){
				_result = AppUtil.operateError();
			}
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
}
