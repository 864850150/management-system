package org.yx.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yx.common.base.BaseController;
import org.yx.common.entity.PageData;
import org.yx.common.utils.AppUtil;
import org.yx.services.WxCartService;
import org.yx.services.WxProductService;
import org.yx.services.WxShopService;

/**
 * 
 * <b>类名：</b>ApiLoginController.java<br>
 * <p><b>标题：</b>芝麻店微信接口</p>
 * <p><b>描述：</b>芝麻店微信接口</p>
 * <p><b>版权声明：</b>Copyright (c) 2017</p>
 * <p><b>公司：</b>上海诣新信息科技有限公司 </p>
 * @author <font color='blue'>zandezhang</font> 
 * @Description 查询店铺信息
 */
@Controller
@RequestMapping(value="/wxShopcar")
public class WxShopCarController extends BaseController{

	@Resource(name="wxCartService")
	private WxCartService wxCartService;
	
	/**
	 * 空购物车最热销三款商品查询
	 * @return
	 */
	@RequestMapping(value="/findKongShopcar")
	@ResponseBody
	public PageData findShop(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			List<PageData> shop=wxCartService.findHotProduct(pd); 
			_result.put("data", shop);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/**
	 * 查询购物车列表
	 * @return
	 */
	@RequestMapping(value="/findShopcarList")
	@ResponseBody
	public PageData findShopcarList(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			List<PageData> shop=wxCartService.findCartList(pd); 
			_result.put("data", shop);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/**
	 *  查询购物车商品数量
	 * @return
	 */
	 
	@RequestMapping(value="/findShopCarCount")
	@ResponseBody
	public PageData findShopProd(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			PageData shop=wxCartService.findCount(pd); 
			_result.put("data", shop);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/**
	 *  删除购物车商品新信息
	 * @return
	 */
	 
	@RequestMapping(value="/delShopcar")
	@ResponseBody
	public PageData delShopcar(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			Object ob=wxCartService.delete(pd); 
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
	 * 新增商品至购物车
	 * @return
	 */
	@RequestMapping(value="/saveShopcar")
	@ResponseBody
	public PageData saveShopcar(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			Object ob= wxCartService.save(pd); 
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
	 * 修改购物车商品信息
	 * @return
	 */
	@RequestMapping(value="/editShopcar")
	@ResponseBody
	public PageData editShopcar(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			Object ob= wxCartService.edit(pd); 
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
