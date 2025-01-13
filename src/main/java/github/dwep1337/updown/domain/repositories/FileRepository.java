package github.dwep1337.updown.domain.repositories;

import github.dwep1337.updown.domain.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {
    File findByReferenceCode(String referenceCode);
}
