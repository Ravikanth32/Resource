package com.cgsinc.producer.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgsinc.producer.model.Resource;
import com.cgsinc.producer.service.ResourceProducerService;

@RestController
public class ResourceProducerController {
	@Autowired
	ResourceProducerService service;

	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("uri") String uri,
			@RequestParam("fileName") String fileName,
			@RequestParam("fileType") String fileType) throws IOException {
		
		Resource resource = new Resource();
		resource.setUri(uri);
		resource.setFileName(fileName);
		resource.setFileType(fileType);
		service.saveResourceService(resource);
		System.out.println(uri);
		return "Successfully Saved Your Data";
	
	}

	@RequestMapping("/search/{content}")
	@ResponseBody
	public Set<Resource> search(@PathVariable("content") String content) {
		//String content="Hi";
		Set<Resource> resource=service.searchService(content);

		return resource;
	}

}
