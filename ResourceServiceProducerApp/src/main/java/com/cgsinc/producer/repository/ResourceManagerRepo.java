package com.cgsinc.producer.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.cgsinc.producer.model.Resource;



@Repository
public class ResourceManagerRepo {
	@Autowired
	CassandraOperations operations;
	@Autowired
	ResourceRepository repo;
	
	
	public void saveResource(Resource resource){
		String query="create table if not exists resources(id uuid ,uri text,fileName text,fileType text,content text,primary key((id),content))";
		operations.query(query);
		//operations.insert(resource);
		repo.save(resource);
		
		
		System.out.println("testing manager Repo");
	}
	public List<Resource> searchContent(){
		List<Resource> resources=(List<Resource>) repo.findAll();
		return resources;
		
	}
	

}
