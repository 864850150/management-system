package org.yx.services;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;







import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yx.common.dao.DaoSupport;
import org.yx.common.entity.PageData;
import org.yx.common.utils.AppUtil;
import org.yx.common.utils.DateUtil;
import org.yx.common.utils.EmptyUtil;
import org.yx.common.utils.RandomNum;
import org.yx.common.utils.UuidUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * 
 * @author zandezhang
 * 店铺信息
 */

@Service("wxOrderService")
public class WxOrderService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
    
	PageData _result = AppUtil.success();
	Object object = null;
	
	/**
	 * 查看订单列表
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public  List<PageData> findList(PageData pd)throws Exception{
		
			Object pageNo = pd.get("PAGENO");
			Object pageSiZe = pd.get("PAGESIZE");
			if(EmptyUtil.isNullOrEmpty(pageNo) || EmptyUtil.isNullOrEmpty(pageSiZe)){
				pd.put("PAGENO", 0);
				pd.put("PAGESIZE", 5);
			}else{
				pd.put("PAGENO", Integer.valueOf(pd.getString("PAGENO")));
				pd.put("PAGESIZE", Integer.valueOf(pd.getString("PAGESIZE")));
				
			}
			
		    List<PageData> de =(List<PageData>) dao.findForList("WxOrderMapper.findOrderList", pd);
		
		return de;
	}
	
	/**
	 * 查看订单总数
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findOrderCount(PageData pd)throws Exception{
		PageData de =(PageData) dao.findForObject("WxOrderMapper.findOrderCount", pd);
	
		return de;
	}
	
	/**
	 * 查看订单销售业绩
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findOrderTotalPrice(PageData pd)throws Exception{
		PageData de =(PageData) dao.findForObject("WxOrderMapper.findOrderTotalPrice", pd);
		
		return de;
	}
	
	/**
	 * 查看订单销售佣金总数
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findCommCount(PageData pd)throws Exception{
		PageData de =(PageData) dao.findForObject("WxOrderMapper.findCommCount", pd);
		
		return de;
	}
	
	/**
	 * 查看销售佣金订单列表
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findCommList(PageData pd)throws Exception{
		
			Object pageNo = pd.get("PAGENO");
			Object pageSiZe = pd.get("PAGESIZE");
			if(EmptyUtil.isNullOrEmpty(pageNo) || EmptyUtil.isNullOrEmpty(pageSiZe)){
				pd.put("PAGENO", 0);
				pd.put("PAGESIZE", 5);
			}else{
				pd.put("PAGENO", Integer.valueOf(pd.getString("PAGENO")));
				pd.put("PAGESIZE", Integer.valueOf(pd.getString("PAGESIZE")));
				
			}
		    List<PageData> de =(List<PageData>) dao.findForList("WxOrderMapper.findCommList", pd);
		 
		return de;
	}
	
	
	
	
	/**
	 * 新增订单
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Object save(PageData pd)throws Exception{
		//新增订单
		String orderid = UuidUtil.get32UUID();
		pd.put("ID",orderid);
		//新建的时候默认状态为00，即待付款状态
		pd.put("ORDER_STATU", "00");
		pd.put("EXPRESS_NAME", "");
		pd.put("EXPRESS_NO", "");
			String ORDER_NO = DateUtil.getTimeStamp()+RandomNum.getFourRandom();
			ORDER_NO = ORDER_NO.substring(2);
			pd.put("ORDER_NO",ORDER_NO);
			object = dao.save("WxOrderMapper.saveOrder", pd);
			//新增订单商品信息
			String COUNT = pd.getString("COUNT");
			PageData pro = new PageData();
			pro.put("ORDER_NO", ORDER_NO);
			for (int i = 0; i < Integer.valueOf(COUNT); i++) {
				pro.put("ID", UuidUtil.get32UUID());
				pro.put("PROD_NUM", pd.get("PROD["+i+"][PROD_NUM]"));
				pro.put("PROD_PRICE", pd.get("PROD["+i+"][PROD_PRICE]"));
				pro.put("PROD_SKUINFO", pd.get("PROD["+i+"][PROD_SKUINFO]"));
				pro.put("PROD_GUIGE", pd.get("PROD["+i+"][PROD_GUIGE]"));
				pro.put("PROD_NO", pd.get("PROD["+i+"][PROD_NO]"));
				object = dao.save("WxOrderMapper.saveOrderPro",pro);
				//修改商品已售数量和商品sku已售数量
				object = dao.save("AppProductMapper.editProSkuNumi",pro);
				object = dao.save("AppProductMapper.editProNumi",pro);
		    }
		return ORDER_NO;
	}
	
	/**
	 * 修改订单状态
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Object editOrderStatu(PageData pd)throws Exception{
	
			String ORDER_STATU = pd.getString("ORDER_STATU");

			object = dao.update("WxOrderMapper.editOrderStatu", pd);
			
			//取消订单时，还原商品已售数量与商品相关sku已售数量
			if(ORDER_STATU.equals("20")){
				List<PageData> or = (List<PageData>) dao.findForList("WxOrderMapper.findOrderProSku", pd);
				for (int i = 0; i < or.size(); i++) {
					//修改商品已售数量和商品sku已售数量
					object = dao.save("AppProductMapper.editProSkuNumd",or.get(i));
					object = dao.save("AppProductMapper.editProNumd",or.get(i));
				}
			}
		return object;
	}
	
	
	/**
	 * 查看订单详情
	 */
	public PageData findDetail(PageData pd)throws Exception{
		PageData odetail = (PageData) dao.findForObject("WxOrderMapper.findOrderDetail", pd);
		
		return odetail;
	}
	
	/**
	 * 确认付款是查询订单信息
	 */
	public PageData findOrderPay(PageData pd)throws Exception{
		PageData odetail = (PageData) dao.findForObject("WxOrderMapper.findOrderPay", pd);
		
		return odetail;
	}

	
	/**
	 * 查询商品是否下架
	 */
	public PageData findProdStatus(PageData pd) throws Exception {
			List<String> list=Arrays.asList(pd.getString("PROD_NO").split(","));
		    List<PageData> odetail = (List<PageData>)dao.findForList("AppOrderMapper.findProdStatus", list);
		    String status="";
		    int flag=0;
		    for(int i=0;i<odetail.size();i++){
		    	status = odetail.get(i).getString("STATUS");
		    	if(!status.equals("00")){
		    		flag=1;
		    		break;
		    	}
		    }
		    PageData re=new PageData();
		    re.put("STATUS", flag);
			return re;
	}
	
	
}
