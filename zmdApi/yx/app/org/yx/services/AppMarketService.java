package org.yx.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.yx.common.dao.DaoSupport;
import org.yx.common.entity.Page;
import org.yx.common.entity.PageData;

@Service("appMarketService")
public class AppMarketService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	//查询project
	public List<PageData> findProject(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("AppMarketMapper.findProject", pd);
	}
	//查询场次、项目、用户名（场次主表）
	public List<PageData> findInfoByCity(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("AppMarketMapper.findInfoByCity", pd);
	}
	//查询项目、场次、用户名（项目主表）
	public List<PageData> findProAndSession(Page page)throws Exception{
		return (List<PageData>)dao.findForList("AppMarketMapper.findProAndSession", page);
	}
	//查询总数
	public PageData findAllCount(Page page)throws Exception{
		return (PageData)dao.findForObject("AppMarketMapper.findAllCount", page);
	}
	//通过proid
	public PageData findProId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AppMarketMapper.findProId", pd);
	}
	//查询user_project
	public List<PageData> findUserPros(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("AppMarketMapper.findUserPros", pd);
	}
	public List<PageData> findUPS(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("AppMarketMapper.findUPS", pd);
	}
	//登录
	public PageData toLogin(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AppMarketMapper.toLogin", pd);
	}
	//检查是否场次是否存在session	
	public PageData findSession(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AppMarketMapper.findSession", pd);
	}
	//检查是否存在user_project	
	public PageData findSupport(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AppMarketMapper.findSupport", pd);
	}
	public void saveSession(PageData pd)throws Exception{
		dao.save("AppMarketMapper.saveSession", pd);
	}
	public void editSession(PageData pd)throws Exception{
		dao.update("AppMarketMapper.editSession", pd);
	}
	
	//保存user_project 
	public void saveSupport(PageData pd)throws Exception{
		dao.save("AppMarketMapper.saveSupport", pd);
	}
	//删除user_project
	public void deleteSupport(PageData pd)throws Exception{
		dao.delete("AppMarketMapper.deleteSupport", pd);
	}
	//changeState--session状态为01
	public void changeState(PageData pd)throws Exception{
		dao.update("AppMarketMapper.changeState", pd);
	}
	
}
