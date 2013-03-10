package com.shop.inventory.web;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.servlet.ServletContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jboss.seam.annotations.In;
import org.jboss.seam.core.Expressions;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.faces.Redirect;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@MappedSuperclass
public abstract class AbstractBackingBean<T> {
	
	protected static Logger log;
	
	@In(required=false)
	protected FacesMessages facesMessages;
	
	@In(required=false)
    protected EntityManager em;
	
	@In(required=false)
	protected Credentials  credentials;
	
	@In(required=false)
	protected Identity identity;
	
	//protected ResourceBundleMessageSource messageSource;
	
	public AbstractBackingBean(Class<T> persistentClass){
		log = LogManager.getLogger(persistentClass);
	}

	protected static ApplicationContext getContextBackingBean(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		ServletContext sc = (ServletContext)ec.getContext();
		ApplicationContext ac = (ApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
		return ac;
	}
	
	protected static ServletContext getServletContext(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		return (ServletContext)ec.getContext();
	}
	
	protected void checkRoleAccess(String expression){
		if(expression != null && expression.trim().length() > 0){
			if (!((Boolean) Expressions.instance().createValueExpression(expression).getValue())) {
				redirectToErrorPage();
			}
		}
	}
	
	protected void redirectToErrorPage(){
		Redirect redirect = Redirect.instance();
		redirect.setViewId("/security_error.xhtml");
		redirect.execute();
	}
	
	protected void forceRedirectPage(String viewId){
		Redirect redirect = Redirect.instance();
		redirect.setViewId(viewId);
		redirect.execute();
	}
}
