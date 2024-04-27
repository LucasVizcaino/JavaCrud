package com.debbugiandoIdeas.companiescrud.services;

import com.debbugiandoIdeas.companiescrud.entity.Company;

public interface CompanyService {

    Company readByName(String name);
    Company create(Company company);
    Company update(Company company, String name);
    void delete(String name);
}
