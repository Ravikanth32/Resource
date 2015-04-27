package com.cgsinc.producer.repository;

import java.util.Set;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.cgsinc.producer.model.Resource;


public interface ResourceRepository extends CassandraRepository<Resource> {
	
	public Iterable<Resource> findAll();
	

}
