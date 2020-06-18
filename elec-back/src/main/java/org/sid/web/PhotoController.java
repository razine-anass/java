package org.sid.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.sid.entities.Photo;
import org.sid.repositories.PhotoRepository;
import org.sid.services.PhotosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "photos")
public class PhotoController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PhotoRepository photoRepository;
	
	@Autowired
	PhotosService photoService;
	
	@GetMapping(path = { "/photo" })
	public ResponseEntity<?> getImages() throws IOException {
		
	    List<Photo> photos=new ArrayList<Photo>();
		try {
			photos = photoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    List<Photo> photosDecomp = new ArrayList<Photo>();
	    Photo photoDecompressée = null;
	    for(Photo ph: photos){
	    	photoDecompressée = new Photo(ph.getId(),ph.getNom(), ph.getType(),decompressBytes(ph.getPicByte()));
	    	photosDecomp.add(photoDecompressée);
	    }
		
	    return ResponseEntity.status(HttpStatus.OK).body(photosDecomp);
	}
	
	@GetMapping(path = { "/{imageName}" })
	public Photo getImageByNom(@PathVariable("imageName") String imageName) throws IOException {
		
		final Optional<Photo> retrievedImage = photoRepository.findByNom(imageName);
		
		Photo img = new Photo(retrievedImage.get().getId(),retrievedImage.get().getNom(), retrievedImage.get().getType(),
				decompressBytes(retrievedImage.get().getPicByte()));
		
		return img;
	}
	
	
	@PostMapping("/upload")
	public ResponseEntity<String> uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		
		Photo img = new Photo(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));
		
		//photoRepository.save(img);
		
		return ResponseEntity.status(HttpStatus.OK).body("image creer");
	}
	
	
	//#################################################################################
	//                             Enregistrement sur disque
	//#################################################################################
	
	@ResponseBody
	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
		String returnValue = "start";
		
		Photo photo = new Photo();
		photo.setFileName(imageFile.getOriginalFilename());
//		photo.setPath("/photo/");
		
		try {
			photoService.saveImage(imageFile, photo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error saving photo", e);
			returnValue = "error";
		}
		
		return returnValue;
	}
	
	@GetMapping(path = "/photos", produces = MediaType.IMAGE_PNG_VALUE)
	public byte [] getPhoto() throws IOException{
		
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/products/"));
	}
	
	@PostMapping(path = "/photos", produces = MediaType.IMAGE_PNG_VALUE)
	public byte [] creatPhoto() throws IOException{
				
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/products/"));
	}
	
	
	//on compresse l'image avant de la stocker en base de donnée
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}
	
	//on décompresse l'image avant de l'envoyer au client
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
}
