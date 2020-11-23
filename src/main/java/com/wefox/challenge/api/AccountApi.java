package com.wefox.challenge.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wefox.challenge.service.AccountService;
import com.wefox.challenge.vo.AccountVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
@RequiredArgsConstructor
public class AccountApi {

  @Autowired
  private AccountService accountService;

  @GetMapping
  public ResponseEntity<List<AccountVO>> findAll() {
    return ResponseEntity.ok(accountService.findAll());
  }

  @PostMapping
  public ResponseEntity<AccountVO> create(@Valid @RequestBody AccountVO account) {
    return ResponseEntity.ok(accountService.save(account));
  }

  @GetMapping("/{id:[0-9]+}")
  public ResponseEntity<AccountVO> findById(@PathVariable Long id) {
    Optional<AccountVO> account = accountService.findById(id);
    if (!account.isPresent()) {
      log.error("Id " + id + " does not exist");
      ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(account.get());
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<AccountVO> findByEmail(@Email @PathVariable String email) {
    Optional<AccountVO> account = accountService.findByEmail(email);
    if (!account.isPresent()) {
      log.error("Email " + email + " does not exist");
      ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(account.get());
  }

  @PutMapping("/{id:[0-9]+}")
  public ResponseEntity<AccountVO> update(@PathVariable Long id,
      @Valid @RequestBody AccountVO account) {
    if (!accountService.findById(id).isPresent()) {
      log.error("Id " + id + " does not exist");
      ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(accountService.save(account));
  }

  @DeleteMapping("/{id:[0-9]+}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    if (!accountService.existsById(id)) {
      log.error("Id " + id + " does not exist");
      ResponseEntity.badRequest().build();
    }

    accountService.deleteById(id);

    return ResponseEntity.ok().build();
  }

}
