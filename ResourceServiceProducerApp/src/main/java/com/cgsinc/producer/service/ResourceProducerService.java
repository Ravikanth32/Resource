package com.cgsinc.producer.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsinc.producer.model.Resource;
import com.cgsinc.producer.repository.ResourceManagerRepo;
import com.cgsinc.producer.repository.ResourceRepository;

@Service
public class ResourceProducerService {
	@Autowired
	ResourceManagerRepo managerRepo;
	@Autowired
	ResourceRepository resRepo;
	
	
	public void saveResourceService(Resource resource) throws IOException{
		
		String content=null;
		String filePath=resource.getUri()+"//"+resource.getFileName()+"."+resource.getFileType();
		System.out.println(filePath);
		if(resource.getFileType().equals("txt")){
		File	file=new File(filePath);
		String destFilePath="E://files2//"+resource.getFileName()+"."+resource.getFileType();
		File destFile=new File(destFilePath);
		FileUtils.copyFile(file, destFile);
		TextScanner ts=new TextScanner();
		 content=ts.scan(file);
		 resource.setId(UUID.randomUUID());
		 resource.setUri(destFilePath);
		 resource.setContent(content);
		
		 managerRepo.saveResource(resource);
		System.out.println(content);
		}
		else if( resource.getFileType().equals("docx")){
			 File	file=new File(filePath);
			File destFile=new File("E://files2//"+resource.getFileName()+"."+resource.getFileType());
			FileUtils.copyFile(file, destFile);
			TextScanner ts=new TextScanner();
			content=ts.scan(file);
			System.out.println(content);
			
		}
		else if( resource.getFileType().equals("pdf")){
			
		}
		else if (resource.getFileType().equals("jpg") || resource.getFileType().equals("jpeg")) {
			String imagePath=resource.getUri()+"//"+resource.getFileName()+"."+resource.getFileType();
			File srcFile = new File(imagePath);
			File destFile = new File("E://files2//" + srcFile.getName());
			FileUtils.copyFile(srcFile, destFile);
			resource.setId(UUID.randomUUID());
			 resource.setUri(imagePath);
			 resource.setContent("image only");
			 managerRepo.saveResource(resource);
			
		}
		
		
		
	}
	
	public Set<Resource> searchService(String content){ 
		
		List<Resource> resources=managerRepo.searchContent();
		Set<Resource> store=new HashSet<Resource>();
		
		int matches=0;
		for(Resource resource:resources){
			String data=resource.getContent();
			 Matcher matcher = Pattern.compile(".*"+content+".*", Pattern.CASE_INSENSITIVE).matcher(data);
			 while (matcher.find()){
				 matches++;
				 if(matches>0){
					 resource.setContent(null);
						store.add(resource);
				 }
			 }
						
		}
		System.out.println(store.size());
		for(Resource resource:store){
			System.out.println(resource.getFileName());
			System.out.println(resource.getUri());
		}
		return store;
	}
	

}
