package com.shop.inventory.web.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.shop.inventory.utils.AppUtils;



@Name("webUtils")
@Scope(ScopeType.APPLICATION)
public class WebUtils extends AppUtils{

	
	public static String getHost(){
		HttpServletRequest  request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();		
		String hostStr = request.getHeader("X-Forwarded-Host");
		if(hostStr==null){
			return  "http://"+request.getServerName();
		}
		return "http://"+hostStr;
	}
	
	public static int getPort(){
		
		HttpServletRequest  request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		return request.getServerPort();
	}
	
	public static String getContextApp(){
		HttpServletRequest  request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();	
    	return request.getContextPath();
	}
	
	public static String getHostContextUrl(){
		
		String context = getHost();
		int port = getPort();
		if(port > 0){
			context += ":"+port;
		}
		context += getContextApp();
		
		return context;
	}
	
	 public static boolean isInternetReachable(String stringURL) {
		try {
			// make a URL to a know source
			URL url = new URL(stringURL);

			// open a connection to that source
			HttpURLConnection urlConnect = (HttpURLConnection) url
					.openConnection();

			// trying to retrieve data from the source.
			// If there is no connection, this line will fail
			@SuppressWarnings("unused")
			Object objData = urlConnect.getContent();
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;                 
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
