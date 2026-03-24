package CRUD.API.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ListingService {
    @Autowired
    ListingRepository listingRepository;

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public Optional<Listing> getListingById(Long id) {
        return listingRepository.findById(id);
    }

    public List<Listing> getListingsByUser(Long userId) {
        return listingRepository.findByUserId(userId);
    }

    public List<Listing> getActiveListings() {
        return listingRepository.findByIsSold(false);
    }

    public List<Listing> searchListings(String title) {
        return listingRepository.findByTitleContaining(title);
    }

    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }

    public Listing updateListing(Long id, Listing listing) {
        listing.setId(id);
        return listingRepository.save(listing);
    }

    public Listing markAsSold(Long id) {
        Optional<Listing> listing = listingRepository.findById(id);
        if (listing.isPresent()) {
            listing.get().setIsSold(true);
            return listingRepository.save(listing.get());
        }
        return null;
    }

    public void deleteListing(Long id) {
        listingRepository.deleteById(id);
    }
}
