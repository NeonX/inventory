package com.shop.inventory.dao;

import java.util.List;

import javax.persistence.Query;

import com.shop.inventory.common.CommonType;
import com.shop.inventory.entity.Tambon;

public class TambonDao extends AbstractGenericDao<Tambon, Integer> {

	public TambonDao() {
		super(TambonDao.class, Tambon.class);
	}

	public List<String> getTambonNameByAmphId(Integer amphId){
		String[] fieldCriteria = {"amphur.amphurId"};
		Object[] filterValue = {amphId};
		return getSingleFieldListWithCriteria("tambonName", fieldCriteria, filterValue, "tambonName", CommonType.ASC);
	}
	
	@SuppressWarnings("unchecked")
	public Tambon getTambon(String tambName, Integer amphId){
		
		try{
			String hql = "FROM Tambon WHERE tambonName = :tambName AND amphur.amphurId = :amphId ";
			Query q = getEntityManager().createQuery(hql);
			q.setParameter("tambName", tambName);
			q.setParameter("amphId", amphId);
			
			List<Tambon> resultList = q.getResultList();
			if(resultList != null && resultList.size() > 0){
				return resultList.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
