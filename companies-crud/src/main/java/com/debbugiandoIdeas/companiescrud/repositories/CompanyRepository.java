package com.debbugiandoIdeas.companiescrud.repositories;

import com.debbugiandoIdeas.companiescrud.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String name);
}
