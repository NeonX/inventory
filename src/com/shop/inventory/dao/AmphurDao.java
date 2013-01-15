package com.shop.inventory.dao;

import java.util.List;

import javax.persistence.Query;

import com.shop.inventory.common.CommonType;
import com.shop.inventory.entity.Amphur;

public class AmphurDao extends AbstractGenericDao<Amphur, Integer> {

	public AmphurDao() {
		super(AmphurDao.class, Amphur.class);
	}

	public List<String> getAmphurNameByProvId(Integer provId){
		String[] fieldCriteria = {"province.provinceId"};
		Object[] filterValue = {provId};
		return getSingleFieldListWithCriteria("amphurName", fieldCriteria, filterValue, "amphurName", CommonType.ASC);
	}
	
	@SuppressWarnings("unchecked")
	public Amphur getAmphur(String amphName,Integer provId){
		
		try{
			String hql = "FROM Amphur WHERE amphurName = :aName AND province.provinceId = :provId ";
			Query q = getEntityManager().createQuery(hql);
			q.setParameter("aName", amphName);
			q.setParameter("provId", provId);
			
			List<Amphur> resultList = q.getResultList();
			if(resultList != null && resultList.size() > 0){
				return resultList.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
