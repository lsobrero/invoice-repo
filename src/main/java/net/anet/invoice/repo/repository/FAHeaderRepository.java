package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.FAHeader;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FAHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FAHeaderRepository extends JpaRepository<FAHeader, Long> {

}
