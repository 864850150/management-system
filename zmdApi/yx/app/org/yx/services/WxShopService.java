package org.yx.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.yx.common.dao.DaoSupport;
import org.yx.common.entity.PageData;

/**
 * 
 * @author zandezhang
 * 店铺信息
 */

@Service("wxShopService")
public class WxShopService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
    
	Object object = null;
	
	//查询店铺详情
	public PageData findDetail(PageData pd)throws Exception{
		
	   return (PageData) dao.findForObject("WxShopMapper.findDetail", pd);
		
	}

	//添加商铺浏览次数
	public Object addView(PageData pd) throws Exception {
		
		object = dao.save("WxShopMapper.addView", pd);
		
		return object;
	}
}
