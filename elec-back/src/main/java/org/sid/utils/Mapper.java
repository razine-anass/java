package org.sid.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// classe de mapping génerique

@Component
public class Mapper<T> {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public DTOEntity convertToDto(T obj, DTOEntity mapper) {
	    return modelMapper.map(obj, mapper.getClass());
	  }
	 
	  public Object convertToEntity(T obj, DTOEntity mapper) {
	    return modelMapper.map(mapper, obj.getClass());
	  }
	  
	  

}
