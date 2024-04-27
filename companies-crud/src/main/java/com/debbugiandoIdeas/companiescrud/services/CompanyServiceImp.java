package com.debbugiandoIdeas.companiescrud.services;

import com.debbugiandoIdeas.companiescrud.entity.Category;
import com.debbugiandoIdeas.companiescrud.entity.Company;
import com.debbugiandoIdeas.companiescrud.entity.WebSite;
import com.debbugiandoIdeas.companiescrud.repositories.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CompanyServiceImp implements CompanyService{

    @Autowired
    private final CompanyRepository companyRepository;


    @Override
    public Company create(Company company) {
        company.getWebSites().forEach(webSite -> {
            if (Objects.isNull(webSite.getCategory())){
                webSite.setCategory(Category.NONE);
            }
        });
        return this.companyRepository.save(company);
    }

    @Override
    public Company readByName(String name) {
        return this.companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
    }


    @Override
    public Company update(Company company, String name) {
        var companyUpdate = this.companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
        companyUpdate.setLogo(company.getLogo());
        companyUpdate.setFounder(company.getFounder());
        companyUpdate.setFoundationDate(company.getFoundationDate());
        return this.companyRepository.save(companyUpdate);
    }

    @Override
    public void delete(String name) {
        var companyDelete = this.companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
        this.companyRepository.delete(companyDelete);
    }
}
