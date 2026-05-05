package CRUD.API.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SavedListingRepository extends JpaRepository<SavedListing, Long> {
    List<SavedListing> findByUserId(Long userId);
    Optional<SavedListing> findByUserIdAndListingId(Long userId, Long listingId);
    boolean existsByUserIdAndListingId(Long userId, Long listingId);
}