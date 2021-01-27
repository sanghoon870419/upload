package com.example.uploadTest.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.uploadTest.entity.RenrakutusuchiList;
import com.example.uploadTest.utils.ExcelData;


public interface RenrakutusuchiListRepository extends JpaRepository<RenrakutusuchiList, String>{
		
	  	//@Query("Select * FROM RenrakutusuchiListRepository  WHERE MATNR = :SCODE")
		List<RenrakutusuchiList> findByMatnr(String matnr);
}
	

