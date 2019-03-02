package osipov.wildberriesparser.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import osipov.wildberriesparser.domain.Item;

public interface ItemRepo extends JpaRepository<Item, Long> {
}