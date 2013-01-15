package com.shop.inventory.web;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.StringUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.shop.inventory.utils.AppUtils;
import com.shop.inventory.web.utils.WebUtils;

@MappedSuperclass
public abstract class AbstractUploadBackingBean<T> extends AbstractBackingBean<T> {

	private ResourceBundleMessageSource msgSrcUpload = (ResourceBundleMessageSource)getContextBackingBean().getBean("messageSourceUpload");
	
	protected String rootPath = msgSrcUpload.getMessage("upload.ROOT_PATH", null, null);
	protected String rootAttach = msgSrcUpload.getMessage("attach.type.ROOT_ATTACH", null, null);
	//protected String rootAttach_surface = msgSrcUpload.getMessage("attach.type.SURFACE_ATTACH",null,null);
	protected int element = 15;
	protected int img_width = 20;
	protected int img_height =20;
	protected int uploadsAvailable = 10;
	protected int fileSizeBytesAllows = 5000000;//5MB Upload Limit
	protected int imgSizeBytesAllows = 800000;//800KB Upload Limit
	protected int imgSizeDamageLevelAllows = 50000;//50KB Upload Limit
	protected final int viewCol = 5;
	protected boolean autoUpload = false;
	protected boolean useFlash = false;
	
	public AbstractUploadBackingBean(Class<T> persistentClass) {
		super(persistentClass);
	}

	public abstract void specifyBean(UploadItem uploadItem);
	
	public void doListenerUpload(UploadEvent uploadEvent)throws Exception{
		UploadItem uploadItem = uploadEvent.getUploadItem();
		specifyBean(uploadItem);
	}
	
	//------resize image-----
	
	public BufferedImage resizeImage(BufferedImage source){
		int sourceWidth = source.getWidth();
		int sourceHeight = source.getHeight();
		double xScale = ((double)img_width)/ (double) sourceWidth;
		double yScale = ((double)img_height)/ (double) sourceHeight;
		
		Graphics2D gp2D = null;
		BufferedImage resizedImage = new BufferedImage(img_width,img_height,BufferedImage.TRANSLUCENT);
		
		log.debug("resizing image to w:"+img_width+" h:"+img_height);
		
		try {
			 gp2D = resizedImage.createGraphics();
			 gp2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			 gp2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			 gp2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			 gp2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
			 gp2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			 gp2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			 
			 AffineTransform aff = AffineTransform.getScaleInstance(xScale, yScale);
			 
			 gp2D.drawRenderedImage(source, aff);
			 
		}finally{
			if(gp2D !=null){
				gp2D.dispose();
			}
		}
		if(source.getType() == BufferedImage.TYPE_BYTE_INDEXED){
			log.debug("reducing to color-indexed image");
			
			BufferedImage indexedImage = new BufferedImage(img_width,img_height,BufferedImage.TYPE_BYTE_INDEXED);
			Graphics  g =null;
			try {
				g = indexedImage.createGraphics();
				g.drawImage(resizedImage,0,0,null);
			} finally {
	            if (g != null)
	                g.dispose();
	        }
			 System.err.println("source" + ((IndexColorModel) source.getColorModel()).getTransparentPixel()
                     + "   " + ((IndexColorModel) indexedImage.getColorModel()).getTransparentPixel());
			
			 return indexedImage;
		}
		return resizedImage;
	}
	//---------------//
	public void savefile(File upfile,String fNameToSave,String filePath)throws Exception{
		//String filename = getFileName(filepath);
		
		String savePath = rootPath;
		savePath += File.separator+filePath+File.separator;
		
		new File(savePath).mkdirs();
		
		//		log.info(">> savePath : "+savePath);
		//		log.info(">> filename : "+filename);
		
		
		File file = new File(savePath, fNameToSave);
		FileOutputStream output = new FileOutputStream(file);
		
		try{
			//########################################
			FileInputStream in = new FileInputStream(upfile);
			while(true){
				int count = in.read();
				
				if(count == -1){
					break;
				}
				output.write(count);
			}
			in.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			output.close();
		}
	}
	public void savefile(File upFile, String filepath)throws Exception{
		
		String filename = getFileName(filepath);
		
		String savePath = rootPath;
		savePath += File.separator+rootAttach+File.separator;
		
		File file = new File(savePath,filename);
		FileOutputStream output = new FileOutputStream(file);

		try{
			FileInputStream in = new FileInputStream(upFile);
			while(true){
				int count = in.read();
				
				if(count == -1){
					break;
				}
				output.write(count);
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			output.close();
		}
	}
	
	public void savefile(File upFile, String filepath, String fNameToSave, String projId, String pageType)throws Exception{
		
		//String filename = getFileName(filepath);
		
		String savePath = rootPath;
		savePath += File.separator+rootAttach;
		savePath += File.separator+projId;
		savePath += File.separator+pageType+File.separator;
		
		new File(savePath).mkdirs();
		
//		log.info(">> savePath : "+savePath);
//		log.info(">> filename : "+filename);
		
		
		File file = new File(savePath, fNameToSave);
		FileOutputStream output = new FileOutputStream(file);
		
		try{
			//########################################
			FileInputStream in = new FileInputStream(upFile);
			while(true){
				int count = in.read();
				
				if(count == -1){
					break;
				}
				output.write(count);
			}
			in.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			output.close();
		}
	}
	
	public String getGenerateFileName(String prefix){
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyyMMdd_HHmmss", new Locale("US")).format(date);
		
		Random rand = new Random();
		if(prefix != null && prefix.trim().length() > 0){
			return prefix+dateStr+"-"+rand.nextInt(10000);
		}else{
			return dateStr+"-"+rand.nextInt(10000);
		}
	}
	
	public String getFileName(String filepath){
//		log.info("===== getFileName IN : "+filepath);
//		if(filepath != null){
//			log.info("===== getFileName OUT : "+new File(filepath).getName());
//			return new File(filepath).getName();
//		}
		
		String[] splitName = StringUtils.split(filepath, "\\");
		if(splitName.length>0){
//			log.info("===== getFileName OUT : "+splitName[splitName.length-1]);
			return splitName[splitName.length-1];
		}
		
		return "";
	}
	
	public String getServletImgUrl(Integer projectId, String fileName, String type){
		
		String url ="";
		String context = WebUtils.getHostContextUrl()+"/attach_file/projattach";
		
		String param1 = "projid="+projectId;
		String param2 = "fname="+fileName;
		String param3 = "ptype="+type;
		
		if(projectId != null){
			url = context+"?"+param1+"&"+param2+"&"+param3;
		}
		
		return url;
	}
	
	public String getServletDownloadUrl(Integer projectId, String fname, String type){
		return AppUtils.getServletDownloadUrl(projectId, fname, type);
	}
	
	public boolean deleteFile(String fileName){
		String pathFileName = rootPath+File.separator+fileName;
		boolean result = false;
		try{
			File delFile = new File(pathFileName);
			if(!delFile.isDirectory()){
				result = delFile.delete();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean deleteFile(String fullPath, String fileName){
		String pathFileName = fullPath+File.separator+fileName;
		boolean result = false;
		try{
			File delFile = new File(pathFileName);
			if(!delFile.isDirectory()){
				result = delFile.delete();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public String getNameWithOutExtension(String fileName){
		if(fileName != null){
			File f = new File(fileName);
			int index = f.getName().lastIndexOf('.');
			if(index > 0){
				return f.getName().substring(0, index);
			}
		}
		return "";
	}
	
	
	public String getFileExtension(String fileName){
		if(fileName != null){
			String[] split = fileName.split("\\.");
			if(split != null && split.length > 0){
				return split[split.length-1];
			}
		}
		return "";
	}
	
	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean getAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	public boolean getUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

	public ResourceBundleMessageSource getMsgSrcUpload() {
		return msgSrcUpload;
	}

	public void setMsgSrcUpload(ResourceBundleMessageSource msgSrcUpload) {
		this.msgSrcUpload = msgSrcUpload;
	}

	public int getViewCol() {
		return viewCol;
	}

	public int getFileSizeBytesAllows() {
		return fileSizeBytesAllows;
	}

	public void setFileSizeBytesAllows(int fileSizeBytesAllows) {
		this.fileSizeBytesAllows = fileSizeBytesAllows;
	}

	public int getImgSizeBytesAllows() {
		return imgSizeBytesAllows;
	}

	public void setImgSizeBytesAllows(int imgSizeBytesAllows) {
		this.imgSizeBytesAllows = imgSizeBytesAllows;
	}

	public int getElement() {
		return element;
	}

	public void setElement(int element) {
		this.element = element;
	}

	public int getImgSizeDamageLevelAllows() {
		return imgSizeDamageLevelAllows;
	}

	public void setImgSizeDamageLevelAllows(int imgSizeDamageLevelAllows) {
		this.imgSizeDamageLevelAllows = imgSizeDamageLevelAllows;
	}
	
}
