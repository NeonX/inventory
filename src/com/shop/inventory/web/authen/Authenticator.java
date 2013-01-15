package com.shop.inventory.web.authen;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import com.pte.remote.service.AuthurizeBeanService;
import com.pte.remote.service.ComponentService;
import com.pte.remote.service.PersonalBeanService;
import com.pte.util.PropertyFileConfig;
import com.shop.inventory.web.AbstractBackingBean;

@Name("authenticator")
public class Authenticator extends AbstractBackingBean<Authenticator> {

	@In Credentials credentials;
	@In Identity identity;
	
	private final String SURVEY_PROJECT = "survey_project";
	private final String CONSTRUCT_PROJECT = "construct_project";
	private final String TRACKING_PROJECT = "tracking_project";
	private final String RESTORATION_PROJECT = "restroration_project";
	private final String BASIC_DATA = "basic_data";
	
	public Authenticator() {
		super(Authenticator.class);
		// TODO Auto-generated constructor stub
	}
	
	public boolean authenticate(){
		try{
			
			return isLoginSuccess(credentials.getUsername(),credentials.getPassword());
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isLoginSuccess(String username,String password){
		
		boolean  isSuccess = false;
		PropertyFileConfig  configWebservice = (PropertyFileConfig)getContextBackingBean().getBean("configWebservice");
		
		try{
			
			ComponentService  componentWebService = (ComponentService)getContextBackingBean().getBean("componentWebService");
			PersonalBeanService  perService = componentWebService.getPersonalBeanService(configWebservice);
	    	Object[] itemAuthen = perService.getItemAfterAuthen(username, password);
			
	    	boolean isRemoteAuthenSuccess = (Boolean)itemAuthen[0];
	    	if(isRemoteAuthenSuccess){

	    		AuthurizeBeanService authorService = componentWebService.getAuthurizeBeanService(configWebservice);
	    		String[] roles = authorService.getMisAuthurizeBean(username, "_", itemAuthen[1].toString(), null);
	    		
	    		boolean canAccessSurveyProject = false;
	    		boolean canAccessConstructProject = false;
	    		boolean canAccessTrackingProject = false;
	    		boolean canAccessRestorationProject = false;
	    		boolean canAccessBasicData = false;
	    		
	    		for(String role : roles){
	    			
	    			identity.addRole(role); 
	    			
	    			if(role.contains(SURVEY_PROJECT) && !canAccessSurveyProject){
	    				canAccessSurveyProject = true;
	    			}else if(role.contains(CONSTRUCT_PROJECT) && !canAccessConstructProject){
	    				canAccessConstructProject = true;
	    			}else if(role.contains(TRACKING_PROJECT) && !canAccessTrackingProject){
	    				canAccessTrackingProject = true;
	    			}else if(role.contains(RESTORATION_PROJECT) && !canAccessRestorationProject){
	    				canAccessRestorationProject = true;
	    			}else if(role.contains(BASIC_DATA) && !canAccessBasicData){
	    				canAccessBasicData = true;
	    			}
	    			
	    		}
	    	
	    		if(canAccessSurveyProject){identity.addRole(SURVEY_PROJECT);}
	    		if(canAccessConstructProject){identity.addRole(CONSTRUCT_PROJECT);}
	    		if(canAccessTrackingProject){identity.addRole(TRACKING_PROJECT);}
	    		if(canAccessRestorationProject){identity.addRole(RESTORATION_PROJECT);}
	    		if(canAccessBasicData){identity.addRole(BASIC_DATA);}
	    		
	    		isSuccess = true;
	    		
	    	}else{
	    		return false;
	    	}
	    	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return isSuccess;
	}

}