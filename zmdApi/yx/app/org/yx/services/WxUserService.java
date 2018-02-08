package org.yx.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yx.common.dao.DaoSupport;
import org.yx.common.entity.PageData;
import org.yx.common.utils.UuidUtil;

@Service("wxUserService")
public class WxUserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	Object object = null;
	/**
	 * 查询用户收货地址列表信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findUserAddressList(PageData pd)throws Exception{
		
		List<PageData> addressList = (List<PageData>) dao.findForList("WxUserMapper.findUserAddressList", pd);
		
		return addressList;
		
	}
	
	/**
	 * 查询用户默认收货地址信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findUserDefaultAddress(PageData pd)throws Exception{
		
		PageData defaultAddress = (PageData) dao.findForObject("WxUserMapper.findUserDefaultAddress", pd);
		
		return defaultAddress;
	}
	
	/**
	 * 新增用户收货地址信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Object saveAddress(PageData pd)throws Exception{
		//对地址进行转码
//		byte[] b = pd.getString("SHOP_AREA").getBytes("gbk");//编码  
//        String sa = new String(b,"utf-8");//解码:用什么字符集编码就用什么字符集解码  
//        System.out.println(sa);  
		String SHOP_AREA   = new String( pd.getString("SHOP_AREA").getBytes("ISO-8859-1") , "UTF-8"); 
		pd.put("SHOP_AREA", SHOP_AREA);
		String SHOP_USERNAME   = new String( pd.getString("SHOP_USERNAME").getBytes("ISO-8859-1") , "UTF-8"); 
		pd.put("SHOP_USERNAME", SHOP_USERNAME);
		String SHOP_ADDRESS   = new String( pd.getString("SHOP_ADDRESS").getBytes("ISO-8859-1") , "UTF-8"); 
		pd.put("SHOP_ADDRESS", SHOP_ADDRESS);
		//将以前的默认地址设为非默认
		object = dao.update("WxUserMapper.editAddressis", pd);
		//新增(自动设改地址为默认)
		String id=UuidUtil.get32UUID();
		pd.put("ID", id);
	    object = dao.save("WxUserMapper.saveAddress", pd);
	    
		return id;
		
	}
	
	
	/**
	 * 编辑用户收货地址信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Object editAddress(PageData pd)throws Exception{
	
		String inputer   = new String( pd.getString("SHOP_AREA").getBytes("ISO-8859-1") , "UTF-8"); 
		pd.put("SHOP_AREA", inputer);
	    object = dao.update("WxUserMapper.editAddress", pd);
		    
		
		return object;
	    
	}
	
	/**
	 * 设置地址为默认
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Object editIsdefault(PageData pd)throws Exception{
		
		//将以前的默认地址设为非默认
		object = dao.update("WxUserMapper.editAddressis", pd);
		
		//设置改地址为默认
	    object = dao.update("WxUserMapper.editIsdefault", pd);
		  
		return object;
		
	}
	
	/**
	 * 删除地址
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Object delAddress(PageData pd)throws Exception{
		
		object = dao.delete("WxUserMapper.delAddress", pd);
		    
			
		return object;
		
	}


}
