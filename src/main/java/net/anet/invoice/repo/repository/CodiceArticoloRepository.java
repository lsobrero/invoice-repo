package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.CodiceArticolo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CodiceArticolo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CodiceArticoloRepository extends JpaRepository<CodiceArticolo, Long> {

}
