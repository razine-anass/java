package org.sid.config;

import org.sid.utils.AppConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

//4.10
@ConfigurationProperties(prefix = AppConstants.FILE_PROPERTIES_PREFIX)
public class FileStrorageProperties {
	
	String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	
}
