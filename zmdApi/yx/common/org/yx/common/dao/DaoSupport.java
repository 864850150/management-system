 package org.yx.common.dao;
 
 import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
 
 @Repository("daoSupport")
 public class DaoSupport
   implements DAO
 {
 
   @Resource(name="sqlSessionTemplate")
   private SqlSessionTemplate sqlSessionTemplate;
 
   @Override
public Object save(String str, Object obj)
     throws Exception
   {
     return Integer.valueOf(this.sqlSessionTemplate.insert(str, obj));
   }
 
   public Object batchSave(String str, List objs)
     throws Exception
   {
     return Integer.valueOf(this.sqlSessionTemplate.insert(str, objs));
   }
 
   @Override
public Object update(String str, Object obj)
     throws Exception
   {
     return Integer.valueOf(this.sqlSessionTemplate.update(str, obj));
   }
 
   public void batchUpdate(String str, List objs)
     throws Exception
   {
     SqlSessionFactory sqlSessionFactory = this.sqlSessionTemplate.getSqlSessionFactory();
 
     SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
     try {
       if (objs != null) {
         int i = 0; for (int size = objs.size(); i < size; i++) {
           sqlSession.update(str, objs.get(i));
         }
         sqlSession.flushStatements();
         sqlSession.commit();
         sqlSession.clearCache();
       }
     } finally {
       sqlSession.close();
     }
   }
 
   public Object batchDelete(String str, List objs)
     throws Exception
   {
     return Integer.valueOf(this.sqlSessionTemplate.delete(str, objs));
   }
 
   @Override
public Object delete(String str, Object obj)
     throws Exception
   {
     return Integer.valueOf(this.sqlSessionTemplate.delete(str, obj));
   }
 
   @Override
public Object findForObject(String str, Object obj)
     throws Exception
   {
	   
     try {
		return this.sqlSessionTemplate.selectOne(str, obj);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
   }
 
   @Override
public Object findForList(String str, Object obj)
     throws Exception
   {
     return this.sqlSessionTemplate.selectList(str, obj);
   }
 
   @Override
public Object findForMap(String str, Object obj, String key, String value) throws Exception {
     return this.sqlSessionTemplate.selectMap(str, obj, key);
   }
 }

/* Location:           F:\掌上幼儿园\源码\yzy_web\WEB-INF\classes\
 * Qualified Name:     com.fh.dao.DaoSupport
 * JD-Core Version:    0.6.2
 */