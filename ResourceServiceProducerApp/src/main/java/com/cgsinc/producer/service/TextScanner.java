package com.cgsinc.producer.service;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class TextScanner implements FileScanner {

	@Override
	public String scan(File file) {
		String content=null;
		try{
		 content=FileUtils.readFileToString(file);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return content;
		

	}

}
