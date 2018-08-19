package com.boco.modules.fdoc.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


  /** 
     * 使用SpringMVC封装好的方法进行文件上传 
     * @param request 
     * @param response 
     * @throws IllegalStateException 
     * @throws IOException 
     */  
 
@Controller()
public class FileController {
	@RequestMapping(value = "/test/upload")
	public String addFile(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request, ModelMap model)
			throws IOException {
		//获取解析器  
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断是否是文件  
        if(resolver.isMultipart(request)){  
            //进行转换  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);  
            //获取所有文件名称  
            Iterator<String> it = multiRequest.getFileNames();  
            while(it.hasNext()){  
                //根据文件名称取文件  
                MultipartFile file1 = multiRequest.getFile(it.next());  
                String fileName = file1.getOriginalFilename();  
                String localPath = "E:/temp/" + fileName;  
                File headPath = new File("E:/temp/");//获取文件夹路径
                if(!headPath.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
                	headPath.mkdirs();
                }
                File newFile = new File(localPath);  
                //上传的文件写入到指定的文件中  
                file1.transferTo(newFile);  
                
                //图片缩放
                Image smallImg = ImageIO.read(newFile);
                int newWidth = 86; 
                int newHeight = 100; 
                BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);  
                
                tag.getGraphics().drawImage(smallImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);  
                FileOutputStream out = new FileOutputStream("E:/temp/" + "small_"+fileName);  
                // JPEGImageEncoder可适用于其他图片类型的转换   
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
                encoder.encode(tag);
                out.close(); 
            }  
        }  
        return "success";
	}
}