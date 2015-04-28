package com.cgsinc.consumer.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;








import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.cgsinc.consumer.model.Resource;


@Controller
public class ResourceConsumerController {
	
		
	@RequestMapping(value="/loginpage")
	
	public String  login(HttpServletRequest request){
		return "login";
	}
	
	
	@RequestMapping("/searchForm")
	public String searchForm(){
		return "searchForm";
	}
	@RequestMapping("/uploadFile")
	public String uploadFile(){
		return "uploadFile";
	}
	@PreAuthorize("hasAuthority('admin')")
	@RequestMapping(value="/homepage" , method = RequestMethod.GET)
	public String home(Principal p){
		System.out.println(p.getName());
		
		return "home";
	}
	
	@RequestMapping("/callingProducer")
	@ResponseBody
	public String callingProducer(@RequestParam("uri") String uri,@RequestParam("fileName")String fileName,@RequestParam("fileType")String fileType){
		System.out.println(uri);
		/*String uri = "E://files1";
		String fileName="sample2";
		String fileType="txt";*/
		String path="http://localhost:2017/ResourceProducerServiceApp/upload?uri="+uri+"&fileName="+fileName+"&fileType="+fileType;
		RestTemplate template=new RestTemplate();
		String message = template.getForObject(path, String.class);
		System.out.println(message);
		
		return message;
		
	}
	
	@RequestMapping("/search")	
	public ModelAndView search(@RequestParam("search") String content,Model model){
	System.out.println(content);
		//String content = "ravi";
		String path="http://localhost:2017/ResourceProducerServiceApp/search/"+content;
		System.out.println("*******************");
		RestTemplate template=new RestTemplate();
		Resource object=null;
		List<Resource> list=new ArrayList<Resource>();
		Set<LinkedHashMap> resource = template.getForObject(path, Set.class);
		for(LinkedHashMap map:resource){
			object=new Resource();
			
			object.setFileName(map.get("fileName").toString());
			object.setFileType(map.get("fileType").toString());
			object.setUri(map.get("uri").toString());
			list.add(object);
		}
		System.out.println(list.size());
		model.addAttribute("list",list);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("viewer");
		
		
		
		return mav;
	}
	
	@RequestMapping("/download")
	public void download(@RequestParam("uri")String uri,@RequestParam("fileType")String fileType,HttpServletResponse response) throws IOException{
		File file=new File(uri);
		InputStream inputStream= new  FileInputStream(file);
		String data=FileUtils.readFileToString(file);
		if(fileType.equals("txt")){
			String fileName = "file.txt"; 
			File downloadFile=new File(fileName);		
			FileUtils.writeStringToFile(downloadFile, data);	
			response.setContentType("application/octet-stream");
	        response.setContentLength((int) downloadFile.length());
	        response.setHeader("Content-Disposition","attachment; filename="+downloadFile.getName());
	        ServletOutputStream out = response.getOutputStream();
	        byte[] bytes = IOUtils.toByteArray(inputStream);
	        FileCopyUtils.copy(bytes, out);        
	        out.flush();
	        out.close();
		}
		else if(fileType.equals("jpg")){
			response.setContentType("image/jpeg");
			IOUtils.copy(inputStream, response.getOutputStream());
		}
		
	}

}
