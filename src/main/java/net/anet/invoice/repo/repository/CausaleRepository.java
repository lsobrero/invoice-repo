package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.Causale;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Causale entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CausaleRepository extends JpaRepository<Causale, Long> {

}
