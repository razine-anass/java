package org.sid.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sid.entities.Photo;
import org.sid.repositories.PhotoRepository;
import org.sid.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class PhotosServiceImpl  extends AbstractService<Photo> implements PhotosService{
	
	@PersistenceContext
	EntityManager entityManager;
	 
	@Autowired
	PhotoRepository photoRepository;
	
	@Override
    protected JpaRepository<Photo, Long> getDao() {
        return photoRepository;
    }
	
	@Override
	public void saveImage(MultipartFile imageFile, Photo photo) throws Exception {
		String folder = "/photo";
		byte[] bytes = imageFile.getBytes();
		
		Path path = Paths.get(folder + imageFile.getOriginalFilename());
		Files.write(path, bytes);
		
	}

}
