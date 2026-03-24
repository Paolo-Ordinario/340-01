package CRUD.API.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/listings")
@CrossOrigin(origins = "*")
public class ListingController {
    @Autowired
    ListingService listingService;

    @GetMapping
    public List<Listing> getAllListings() {
        return listingService.getAllListings();
    }

    @GetMapping("/active")
    public List<Listing> getActiveListings() {
        return listingService.getActiveListings();
    }

    @GetMapping("/user/{userId}")
    public List<Listing> getListingsByUser(@PathVariable Long userId) {
        return listingService.getListingsByUser(userId);
    }

    @GetMapping("/search")
    public List<Listing> searchListings(@RequestParam String title) {
        return listingService.searchListings(title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getListingById(@PathVariable Long id) {
        Optional<Listing> listing = listingService.getListingById(id);
        if (listing.isPresent()) {
            return ResponseEntity.ok(listing.get());
        } else {
            return ResponseEntity.status(404).body("Listing with ID " + id + " not found");
        }
    }

    @PostMapping
    public ResponseEntity<Object> createListing(@RequestBody Listing listing) {
        Listing saved = listingService.createListing(listing);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateListing(@PathVariable Long id, @RequestBody Listing listing) {
        Optional<Listing> existing = listingService.getListingById(id);
        if (existing.isPresent()) {
            Listing updated = listingService.updateListing(id, listing);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(404).body("Listing not found.");
        }
    }

    @PutMapping("/{id}/sold")
    public ResponseEntity<Object> markAsSold(@PathVariable Long id) {
        Listing updated = listingService.markAsSold(id);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(404).body("Listing not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteListing(@PathVariable Long id) {
        Optional<Listing> existing = listingService.getListingById(id);
        if (existing.isPresent()) {
            listingService.deleteListing(id);
            return ResponseEntity.ok("Listing deleted.");
        } else {
            return ResponseEntity.status(404).body("Listing not found.");
        }
    }
}
