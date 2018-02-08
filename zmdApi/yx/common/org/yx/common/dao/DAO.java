package org.yx.common.dao;

public abstract interface DAO
{
  public abstract Object save(String paramString, Object paramObject)
    throws Exception;

  public abstract Object update(String paramString, Object paramObject)
    throws Exception;

  public abstract Object delete(String paramString, Object paramObject)
    throws Exception;

  public abstract Object findForObject(String paramString, Object paramObject)
    throws Exception;

  public abstract Object findForList(String paramString, Object paramObject)
    throws Exception;

  public abstract Object findForMap(String paramString1, Object paramObject, String paramString2, String paramString3)
    throws Exception;
}

/* Location:           F:\掌上幼儿园\源码\yzy_web\WEB-INF\classes\
 * Qualified Name:     com.fh.dao.DAO
 * JD-Core Version:    0.6.2
 */