package com.vote.vote.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.vote.vote.config.StorageConfig;
import com.vote.vote.exception.StorageException;
import com.vote.vote.exception.StorageFileNotFoundException;



@Service
public class StorageService {
	private final Path rootLocation; // 파일의 저장 위치
	
	@Autowired
	public StorageService(StorageConfig properties) {// 파일 저장 장소를  Config 로 부터 받음
		this.rootLocation = Paths.get(properties.getLocation());
	}
	
	public void store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new StorageException("빈 파일: "+ filename);
			}
			if(filename.contains("..")) {
				throw new StorageException("파일 이름 오류: "+filename);
			}
			try (InputStream inputStream = file.getInputStream()) {     
				Files.copy(inputStream, this.rootLocation.resolve(filename),      
				StandardCopyOption.REPLACE_EXISTING);   // 중복된 이름의 사진이 들어올 경우 덮어 쓰는 형식으로 해당 위치에 복사함
				} 
		}catch(IOException e) {
			throw new StorageException("저장 실패 "+filename, e);
		}
	}
	
	public Stream<Path> loadAll(){ // 모든 파일을 로딩
		try {
			return Files.walk(this.rootLocation, 1)
				.filter(path ->!path.equals(this.rootLocation))
				.map(this.rootLocation::relativize); // return Stream<Path>
		}
		catch(IOException e) {
			throw new StorageException("FAiled to read stored files",e);
		}
	}
	
	public Path load(String filename) { // 하나의 파일의 패스를
		return rootLocation.resolve(filename);
	}
	
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename); // 파일 로딩함.
			Resource resource =  new UrlResource(file.toUri()); 
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException("Could not read file: "+filename);
			}
		}
		catch(MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: "+filename, e);
		}
	}
	
	public void deleteAll() { // 전부 삭제
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
	public void init() { // 파일들을 저장할 폴더를 만듦
		try {
			Files.createDirectories(rootLocation);
		}
		catch(IOException e) {
			throw new StorageException("COuld not initialize storage", e);
		}
	}
}
