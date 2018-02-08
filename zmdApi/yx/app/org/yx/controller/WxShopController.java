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
@RequestMapping(value="/wxShop")
public class WxShopController extends BaseController{

	@Resource(name="wxShopService")
	private WxShopService wxShopService;
	
	@Resource(name="wxProductService")
	private WxProductService wxProductService;
	
	/**
	 * 查询店铺信息
	 * @return
	 */
	@RequestMapping(value="/findShop")
	@ResponseBody
	public PageData findShop(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			PageData shop=wxShopService.findDetail(pd); 
			_result.put("data", shop);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/*
	 * 查询店铺商品列表
	 */
	@RequestMapping(value="/findShopProd")
	@ResponseBody
	public PageData findShopProd(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			List<PageData> shop=wxProductService.findList(pd); 
			_result.put("data", shop);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	/**
	 * 查询商品详情
	 * @return
	 */
	@RequestMapping(value="/findProdDetail")
	@ResponseBody
	public PageData findProdDetail(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			PageData prod=wxProductService.findDetail(pd); 
			_result.put("data", prod);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/**
	 * 查询商品型号详情
	 * @return
	 */
	@RequestMapping(value="/findProdxhDetail")
	@ResponseBody
	public PageData findProdxhDetail(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			PageData prod=wxProductService.findXDetail(pd); 
			_result.put("data", prod);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
	/**
	 * 查询商品型号详情
	 * @return
	 */
	@RequestMapping(value="/findProdSku")
	@ResponseBody
	public PageData findProdSku(){
		PageData pd=new PageData();
		pd=getPageData();
		PageData _result=AppUtil.success();
		try {
			/*String SKU_INFO = new String(pd.getString("SKU_INFO").getBytes("ISO-8859-1"), "utf8"); 
			pd.put("SKU_INFO", SKU_INFO);*/
			PageData prod=wxProductService.findShopBySku(pd); 
			_result.put("data", prod);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}


	/*
	 * 添加浏览商铺的浏览次数
	 */
	@RequestMapping(value="/addView")
	@ResponseBody
	public PageData addView(){
		PageData pd=new PageData();
		pd=getPageData();
		Date now1 = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String format = sdf.format(now1);
        pd.put("ID", get32UUID());
        pd.put("DATE", format);
		PageData _result=AppUtil.success();
		try {
			Object addView = wxShopService.addView(pd); 
			_result.put("data", addView);
		} catch (Exception e) {
			e.printStackTrace();
			_result = AppUtil.otherError();
		}
		
		return _result;
	}
	
}
