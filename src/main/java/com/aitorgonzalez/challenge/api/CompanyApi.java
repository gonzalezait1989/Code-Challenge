package com.aitorgonzalez.challenge.api;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aitorgonzalez.challenge.exceptions.InvalidRequestException;
import com.aitorgonzalez.challenge.exceptions.ResourceNotFoundException;
import com.aitorgonzalez.challenge.service.CompanyService;
import com.aitorgonzalez.challenge.vo.CompanyVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/companies")
@Slf4j
@RequiredArgsConstructor
public class CompanyApi {

	@Autowired
	private CompanyService companyService;

	@PostMapping(produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<CompanyVO> create(@Valid @RequestBody CompanyVO company, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		if (companyService.findByName(company.getName()).isPresent()) {
			bindingResult.rejectValue("name", "DUPLICATED", "company name already exists");
			throw new InvalidRequestException(bindingResult);
		}
		return ResponseEntity.ok(companyService.save(company));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		return companyService.findById(id).map(companyFromDB -> {
			companyService.deleteById(companyFromDB.getId());
			return ResponseEntity.noContent().build();
		}).orElseThrow(ResourceNotFoundException::new);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<HashMap<String, Object>> findAll() {
		List<CompanyVO> companies = companyService.findAll();
		HashMap<String, Object> map = new HashMap<>();
		map.put("companies", companies);
		return ResponseEntity.ok(map);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<CompanyVO> replace(@Valid @RequestBody CompanyVO company, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		return ResponseEntity.ok(companyService.save(company));
	}
}
