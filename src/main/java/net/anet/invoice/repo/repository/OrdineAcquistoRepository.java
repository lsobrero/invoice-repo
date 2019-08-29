package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.OrdineAcquisto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrdineAcquisto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdineAcquistoRepository extends JpaRepository<OrdineAcquisto, Long> {

}
