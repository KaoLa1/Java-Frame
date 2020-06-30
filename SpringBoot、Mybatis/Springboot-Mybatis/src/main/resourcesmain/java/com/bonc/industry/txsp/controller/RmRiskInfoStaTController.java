package com.bonc.industry.txsp.controller;

import java.util.List;

import com.bonc.industry.txsp.service.RmRiskInfoStaTService;
import com.bonc.industry.txsp.entity.RmRiskInfoStaT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

/**
 * 	控制层类
 *	??????Ϣͳ?Ʊ
 * 
 * @author admin
 * @email admin@bonc.com.cn
 * @date 2020-03-19 15:36:00
 */
@RestController
@RequestMapping("rmRiskInfoStaTs")
public class RmRiskInfoStaTController {

	@Autowired
	RmRiskInfoStaTService rmRiskInfoStaTService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity findRmRiskInfoStaTList(RmRiskInfoStaT rmRiskInfoStaT, @RequestParam(value="pageSize", required=true, defaultValue="10") Integer pageSize, @RequestParam(value="pageNum", required=true, defaultValue="1") Integer pageNum) {
		return ResponseEntity.ok(rmRiskInfoStaTService.query(rmRiskInfoStaT, pageSize, pageNum));
	}

	@GetMapping("/{statisticId}")
	@ResponseBody
	public ResponseEntity<RmRiskInfoStaT> findRmRiskInfoStaT( @PathVariable("statisticId") String statisticId) {
		return ResponseEntity.ok(rmRiskInfoStaTService.findById(statisticId));
	}
	
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody RmRiskInfoStaT rmRiskInfoStaT) {		
		return ResponseEntity.ok(rmRiskInfoStaTService.create(rmRiskInfoStaT));
	}
	
	@PutMapping()
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody RmRiskInfoStaT rmRiskInfoStaT) {
		return ResponseEntity.ok(rmRiskInfoStaTService.update(rmRiskInfoStaT));
	}
	
	@DeleteMapping("/{statisticId}")
	@ResponseBody
	public ResponseEntity<?> deleteByIdRmRiskInfoStaT( @PathVariable("statisticId") String statisticId) {
		return ResponseEntity.ok(rmRiskInfoStaTService.deleteById(statisticId));
	}

    @DeleteMapping()
    @ResponseBody
    public ResponseEntity<?> deleteBatchRmRiskInfoStaT(@RequestParam String statisticId) {
        return ResponseEntity.ok(rmRiskInfoStaTService.deleteBatch(statisticId));
    }
}