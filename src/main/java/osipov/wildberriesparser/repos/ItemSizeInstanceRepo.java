package osipov.wildberriesparser.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import osipov.wildberriesparser.domain.ItemSizeInstance;

public interface ItemSizeInstanceRepo extends JpaRepository<ItemSizeInstance, Long> {
}
