package CRUD.API.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/saved")
@CrossOrigin(origins = "*")
public class SavedListingController {

    @Autowired
    SavedListingService savedListingService;

    @GetMapping("/{userId}")
    public List<SavedListing> getSaved(@PathVariable Long userId) {
        return savedListingService.getSavedByUser(userId);
    }

    @GetMapping("/{userId}/{listingId}")
    public ResponseEntity<Boolean> isSaved(@PathVariable Long userId, @PathVariable Long listingId) {
        return ResponseEntity.ok(savedListingService.isSaved(userId, listingId));
    }

    @PostMapping("/{userId}/{listingId}")
    public ResponseEntity<Object> save(@PathVariable Long userId, @PathVariable Long listingId) {
        if (savedListingService.isSaved(userId, listingId)) {
            return ResponseEntity.status(400).body("Already saved.");
        }
        return ResponseEntity.status(201).body(savedListingService.save(userId, listingId));
    }

    @DeleteMapping("/{userId}/{listingId}")
    public ResponseEntity<Object> unsave(@PathVariable Long userId, @PathVariable Long listingId) {
        if (!savedListingService.isSaved(userId, listingId)) {
            return ResponseEntity.status(404).body("Not saved.");
        }
        savedListingService.unsave(userId, listingId);
        return ResponseEntity.ok("Removed from saved.");
    }
}