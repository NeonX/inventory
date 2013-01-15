package com.shop.inventory.dao;

import java.util.List;

import javax.persistence.Query;

import com.shop.inventory.common.CommonType;
import com.shop.inventory.entity.Province;

public class ProvinceDao extends AbstractGenericDao<Province, Integer> {

	public ProvinceDao() {
		super(ProvinceDao.class, Province.class);
	}

	public List<String> getAllProvName(){
		return getSingleFieldListWithCriteria("provinceName", null, null, "provinceName", CommonType.ASC);
	}
	
	@SuppressWarnings("unchecked")
	public Province getProvinceByName(String name){
		try{
			String hql = "FROM Province WHERE provinceName = :name  ";
			Query q = getEntityManager().createQuery(hql);
			q.setParameter("name", name);
			List<Province> resultList = q.getResultList();
			if(resultList != null && resultList.size() > 0){
				return resultList.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
