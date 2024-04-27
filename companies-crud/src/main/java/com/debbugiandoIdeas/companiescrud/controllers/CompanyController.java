package com.debbugiandoIdeas.companiescrud.controllers;

import com.debbugiandoIdeas.companiescrud.entity.Company;
import com.debbugiandoIdeas.companiescrud.services.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping(path = "company")
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping(path = "{name}")
    public ResponseEntity<Company> get(@PathVariable String name){
        log.info("GET: company{}", name);
        return ResponseEntity.ok(this.companyService.readByName(name));
    }

    @PostMapping
    public ResponseEntity<Company> post(@RequestBody Company company){
        log.info("POST: company{}", company.getName());
        return ResponseEntity.created(URI.create(this.companyService.create(company).getName())).build();
    }

    @PutMapping(path = "{name}")
    public ResponseEntity<Company> put(@RequestBody Company company,@PathVariable String name){
        log.info("PUT: company{}", name);
        return ResponseEntity.ok(this.companyService.update(company, name));
    }
}
