package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.FABody;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FABody entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FABodyRepository extends JpaRepository<FABody, Long> {

}
