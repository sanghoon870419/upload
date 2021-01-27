package com.spring.upload.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.upload.entity.DBConnectEntity;
import com.spring.upload.vo.ExcelData;

public interface ExcelRepository extends JpaRepository<DBConnectEntity, String> {

	
}
