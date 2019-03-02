package osipov.wildberriesparser.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import osipov.wildberriesparser.domain.User;

public interface UserRepo extends JpaRepository<User,Long> {
}
