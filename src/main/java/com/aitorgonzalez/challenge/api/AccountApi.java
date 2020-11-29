package com.aitorgonzalez.challenge.api;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.aitorgonzalez.challenge.service.AccountService;
import com.aitorgonzalez.challenge.vo.AccountVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountApi {

	@Autowired
	private AccountService accountService;

	@PostMapping
	public ResponseEntity<AccountVO> create(@Valid @RequestBody AccountVO account, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		if (accountService.findByEmail(account.getEmail()).isPresent()) {
			bindingResult.rejectValue("email", "DUPLICATED", "account email already exists");
			throw new InvalidRequestException(bindingResult);
		}

		return ResponseEntity.ok(accountService.save(account));
	}

	@DeleteMapping("/{id:[0-9]+}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		return accountService.findById(id).map(accountFromDB -> {
			accountService.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElseThrow(ResourceNotFoundException::new);
	}

	@GetMapping
	public ResponseEntity<HashMap<String, Object>> findAll() {
		List<AccountVO> accounts = accountService.findAll();
		HashMap<String, Object> map = new HashMap<>();
		map.put("accounts", accounts);
		return ResponseEntity.ok(map);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<AccountVO> findByEmail(@Email @PathVariable String email) {
		return accountService.findByEmail(email).map(ResponseEntity::ok).orElseThrow(ResourceNotFoundException::new);
	}

	@GetMapping("/{id:[0-9]+}")
	public ResponseEntity<AccountVO> findById(@PathVariable Long id) {
		return accountService.findById(id).map(ResponseEntity::ok).orElseThrow(ResourceNotFoundException::new);
	}

	@PutMapping("/{id:[0-9]+}")
	public ResponseEntity<AccountVO> update(@PathVariable Long id, @Valid @RequestBody AccountVO account) {
		return accountService.findById(id).map(accountFromDB -> {
			AccountVO updatedAccount = accountService.save(account);
			return ResponseEntity.ok(updatedAccount);
		}).orElseThrow(ResourceNotFoundException::new);
	}
}
