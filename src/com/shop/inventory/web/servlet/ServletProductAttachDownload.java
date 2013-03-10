/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.inventory.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shop.inventory.common.AttachType;

public class ServletProductAttachDownload extends HttpServlet {

	private static final long serialVersionUID = -7109770304409087387L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }    
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ResourceBundleMessageSource messageSourceUpload = (ResourceBundleMessageSource)ac.getBean("messageSourceUpload");
		
		String fname = "";
		String projId = "";
		String ptype = "";
		
		try{
			fname = request.getParameter("fname");
			projId = request.getParameter("projid");
			ptype = request.getParameter("ptype");
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		String rootPath = messageSourceUpload.getMessage("upload.ROOT_PATH", null, null);
		String rootAttach = messageSourceUpload.getMessage("attach.type.ROOT_ATTACH", null, null);
		String filePath = rootPath+File.separator+rootAttach+File.separator+projId+File.separator+ptype;
		
		if(ptype.equalsIgnoreCase(AttachType.BLANK)){
			filePath = rootPath+File.separator+rootAttach+File.separator+"blank.png";
		}else{
			filePath += File.separator+fname;
		}
		
		FileInputStream fileToDownload = new FileInputStream(filePath);
		ServletOutputStream output = response.getOutputStream();
		
		String contentType = getServletContext().getMimeType(filePath);
		response.setContentType(contentType);
		
		response.setHeader("Cache-Control", "no cache");
		
		if (!"image/bmp".equals(contentType)
				&& !"image/jpeg".equals(contentType)
				&& !"image/gif".equals(contentType)
				&& !"image/png".equals(contentType)) {
			response.addHeader("Content-Disposition", "attachment;filename="+fname);
		}
		
		response.addHeader("pragma", "public");
		response.setContentLength(fileToDownload.available());
		int c;
		while ((c = fileToDownload.read()) != -1) {
			output.write(c);
		}
		fileToDownload.close();
		output.flush();
		output.close();
		
    }
    
   
}



