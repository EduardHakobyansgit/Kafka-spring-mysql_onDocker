package com.apollon.entry_service.service;


import com.apollon.entry_service.model.Entry;
import com.apollon.entry_service.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class SavingEntry {

    private final EntryRepository entryRepository;

    /**
     * This method saves entry into repository
     * @param newEntry
     * @return
     */
    public void saveEntry(JSONObject newEntry) {
        Entry entryForSave = new Entry();
        entryForSave.setApi(newEntry.get("API").toString());
        entryForSave.setDescription(newEntry.get("Description").toString());
        entryForSave.setCategory(newEntry.get("Category").toString());
        entryForSave.setHttps(newEntry.get("HTTPS").toString());
        entryForSave.setAuth(newEntry.get("Auth").toString());
        entryForSave.setCors(newEntry.get("Cors").toString());
        entryForSave.setLink(newEntry.get("Link").toString());
        entryRepository.save(entryForSave);

    }
}
