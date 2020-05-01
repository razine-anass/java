package org.sid.repositories;

import java.util.Optional;

import org.sid.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

	Optional<Photo> findByNom(String nom);

}
