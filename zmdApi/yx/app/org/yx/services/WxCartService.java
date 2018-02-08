package org.yx.services;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yx.common.dao.DaoSupport;
import org.yx.common.entity.PageData;
import org.yx.common.utils.AppUtil;
import org.yx.common.utils.EmptyUtil;
import org.yx.common.utils.UuidUtil;

import com.alibaba.fastjson.JSONArray;

/**
 * 
 * @author zandezhang
 * 店铺信息
 */

@Service("wxCartService")
public class WxCartService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
    
	PageData _result = AppUtil.success();
	Object object = null;
	
	/**
	 * 查看购物车
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findCartList(PageData pd)throws Exception{
		List<PageData> de =(List<PageData>) dao.findForList("WxCartMapper.findCartList", pd);
		return de;
	}
	
	/**
	 * 查询购物车商品总数
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findCount(PageData pd)throws Exception{
		PageData de =(PageData) dao.findForObject("WxCartMapper.findCount", pd);
		
		return de;
	}
	
	/**
	 * 空购物车最热销三款商品查询
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findHotProduct(PageData pd)throws Exception{
		List<PageData> de =(List<PageData>) dao.findForList("WxProductMapper.findHotProduct", pd);
		
		return de;
	}
	
	
	
	/**
	 * 新增购物车
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Object save(PageData pd)throws Exception{
		
		//查询该商品是否已存在该用户的购物车中
		PageData pro= (PageData) dao.findForObject("WxCartMapper.findIsProd", pd);
		if(!EmptyUtil.isNullOrEmpty(pro)){
			pd.put("ID", pro.get("ID"));
			pd.put("PROD_NUM", (Integer.valueOf(pro.get("PROD_NUM").toString())+ Integer.valueOf(pd.get("PROD_NUM").toString())));
			object = dao.update("WxCartMapper.editCart", pd);
		}else{
			pd.put("ID", UuidUtil.get32UUID());
			object = dao.save("WxCartMapper.saveCart", pd);
			
		}
		return object;
		
	}
	
	/**
	 * 修改购物车
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Object edit(PageData pd)throws Exception{
		
		String COUNT = pd.getString("COUNT");
		PageData pro = new PageData();
		PageData pro1 = new PageData();
		pro1.put("USER_ID", pd.get("USER_ID"));
		
		for (int i = 0; i < Integer.valueOf(COUNT); i++) {
			pro.put("ID", pd.get("PROD["+i+"][ID]"));
			pro.put("ATTRBUTE", pd.get("PROD["+i+"][ATTRBUTE]"));
			pro.put("PROD_NUM", pd.get("PROD["+i+"][PROD_NUM]"));
			pro.put("SKU_INFO", pd.get("PROD["+i+"][SKU_INFO]"));
			

			
			pro1.put("PROD_NO", pd.get("PROD["+i+"][PROD_NO]"));
			pro1.put("SKU_INFO", pd.get("PROD["+i+"][SKU_INFO]"));
			//查询该商品是否已存在该用户的购物车中
			PageData PO= (PageData) dao.findForObject("WxCartMapper.findIsProd", pro1);
			if(!EmptyUtil.isNullOrEmpty(PO)){
				if(PO.getString("ID").equals(pro.getString("ID")))
				{
					//修改
					object = dao.update("AppCartMapper.editCart", pro);
				}else{
					PO.put("PROD_NUM", (Integer.valueOf(PO.get("PROD_NUM").toString())+ Integer.valueOf(pro.get("PROD_NUM").toString())));
					//删除当前购物车商品信息
					object = dao.delete("AppCartMapper.deleteCart", pro);
					//修改 商品数量累加
					object = dao.update("AppCartMapper.editCart", PO);
				}
			}else{
				object = dao.update("AppCartMapper.editCart", pro);
			}
	    }
		return object;
	}
	
	
	/**
	 * 删除购物车
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Object delete(PageData pd)throws Exception{
	    
		object = dao.delete("WxCartMapper.deleteCart", pd);
		 
		return object;
	}
	
	
}
