package org.yx.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.yx.common.dao.DaoSupport;
import org.yx.common.entity.PageData;
import org.yx.common.utils.EmptyUtil;

/**
 * 
 * @author zandezhang
 * 店铺商品信息
 */

@Service("wxProductService")
public class WxProductService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
    
	Object object = null;
	//查询店铺商品列表
	public List<PageData> findList(PageData pd)throws Exception{
			Object pageNo = pd.get("PAGENO");
			Object pageSiZe = pd.get("PAGESIZE");
			if(EmptyUtil.isNullOrEmpty(pageNo) || EmptyUtil.isNullOrEmpty(pageSiZe)){
				pd.put("PAGENO", 0);
				pd.put("PAGESIZE", 5);
			}else{
				pd.put("PAGENO", Integer.valueOf(pd.getString("PAGENO")));
				pd.put("PAGESIZE", Integer.valueOf(pd.getString("PAGESIZE")));
				
			}
		    List<PageData> de =(List<PageData>) dao.findForList("WxProductMapper.findList", pd);
		 
		return de;
	}
	
	//查询店铺商品详情
	public PageData findDetail(PageData pd)throws Exception{
		PageData de =(PageData) dao.findForObject("WxProductMapper.findDetail", pd);
		
		return de;
	}
	
	//查询店铺商品型号详情
	public PageData findXDetail(PageData pd)throws Exception{
		PageData de =(PageData) dao.findForObject("WxProductMapper.findXDetail", pd);
		
		return de;
	}
	
	//查询用户店铺商品对象sku信息
	public PageData findShopBySku(PageData pd)throws Exception{
		PageData de =(PageData) dao.findForObject("WxProductMapper.findShopBySku", pd);
		
		return de;
	}
	
	
	
	
	
}
