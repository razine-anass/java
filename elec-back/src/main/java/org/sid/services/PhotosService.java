package org.sid.services;

import org.sid.entities.Photo;
import org.sid.services.common.IOperations;
import org.springframework.web.multipart.MultipartFile;

public interface PhotosService extends IOperations<Photo> {

	void saveImage(MultipartFile imageFile, Photo photo) throws Exception;

}
