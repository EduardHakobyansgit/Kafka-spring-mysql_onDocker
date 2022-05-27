package com.apollon.entry_service.repository;


import com.apollon.entry_service.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {


}
