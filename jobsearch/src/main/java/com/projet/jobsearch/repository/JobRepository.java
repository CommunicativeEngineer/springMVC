package com.projet.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.jobsearch.entities.Job;

public interface JobRepository extends JpaRepository<Job, String>{

}
