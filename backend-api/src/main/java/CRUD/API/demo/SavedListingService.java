package CRUD.API.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SavedListingService {

    @Autowired
    SavedListingRepository savedListingRepository;

    public List<SavedListing> getSavedByUser(Long userId) {
        return savedListingRepository.findByUserId(userId);
    }

    public boolean isSaved(Long userId, Long listingId) {
        return savedListingRepository.existsByUserIdAndListingId(userId, listingId);
    }

    public SavedListing save(Long userId, Long listingId) {
        SavedListing saved = new SavedListing();
        saved.setUserId(userId);
        saved.setListingId(listingId);
        return savedListingRepository.save(saved);
    }

    public void unsave(Long userId, Long listingId) {
        Optional<SavedListing> existing = savedListingRepository.findByUserIdAndListingId(userId, listingId);
        existing.ifPresent(s -> savedListingRepository.deleteById(s.getId()));
    }
}