package org.yx.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yx.common.base.BaseController;
import org.yx.common.entity.PageData;
import org.yx.common.utils.AppUtil;
import org.yx.services.WxOrderService;
import org.yx.services.WxProductService;
import org.yx.services.WxShopService;

import com.alibaba.fastjson.JSON;

/**
 * 
 * <b>类名：</b>ApiLoginController.java<br>
 * <p><b>标题：</b>芝麻店微信接口</p>
 * <p><b>描述：</b>芝麻店微信接口</p>
 * <p><b>版权声明：</b>Copyright (c) 2017</p>
 * <p><b>公司：</b>上海诣新信息科技有限公司 </p>
 * @author <font color='blue'>zandezhang</font> 
 * @Description 微信用户订单信息
 */
@Controller
@RequestMapping(value="/wxOrder")
public class WxOrderController extends BaseController{

	@Resource(name="wxOrderService")
	private WxOrderService wxOrderService;
	
	/**
	 * 查询微信用户订单列表
	 * @return
	 */
	@RequestMapping(value="/findOrderList")
	@ResponseBody
	public PageData findOrderList(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			List<PageData> oList=(List<PageData>) wxOrderService.findList(pd); 
			_result.put("data", oList);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/**
	 * 查询微信用户订单详情
	 * @return
	 */
	@RequestMapping(value="/findOrderDetail")
	@ResponseBody
	public PageData findOrderDetail(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			PageData count=(PageData) wxOrderService.findDetail(pd); 
			_result.put("data", count);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/**
	 * 确认付款时查询订单信息
	 * @return
	 */
	@RequestMapping(value="/findOrderPay")
	@ResponseBody
	public PageData findOrderPay(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			PageData count=(PageData) wxOrderService.findOrderPay(pd); 
			_result.put("data", count);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/**
	 * 查询微信用户订单数量
	 * @return
	 */
	@RequestMapping(value="/findOrderCount")
	@ResponseBody
	public PageData findOrderCount(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			PageData count=(PageData) wxOrderService.findOrderCount(pd); 
			_result.put("data", count);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/**
	 * 修改订单状态
	 * @return
	 */
	@RequestMapping(value="/editStatu")
	@ResponseBody
	public PageData editStatu(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			Object ob= wxOrderService.editOrderStatu(pd); 
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
	 * 新增订单信息
	 * @return
	 */
	@RequestMapping(value="/saveOrder")
	@ResponseBody
	public PageData saveOrder(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			Object ob= wxOrderService.save(pd); 
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
	 * 查询订单中商品是否下架
	 * @return
	 */
	@RequestMapping(value="/findProdStatus")
	@ResponseBody
	public PageData findProdStatus(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			PageData count=(PageData) wxOrderService.findProdStatus(pd); 
			_result.put("data", count);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}


}
