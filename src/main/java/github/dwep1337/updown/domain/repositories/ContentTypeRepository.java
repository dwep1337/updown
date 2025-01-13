package github.dwep1337.updown.domain.repositories;

import github.dwep1337.updown.domain.entity.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentTypeRepository extends JpaRepository<ContentType, Long> {
    ContentType findByContentType(String contentType);
}
