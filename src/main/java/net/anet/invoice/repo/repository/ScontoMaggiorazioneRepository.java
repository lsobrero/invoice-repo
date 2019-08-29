package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.ScontoMaggiorazione;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ScontoMaggiorazione entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScontoMaggiorazioneRepository extends JpaRepository<ScontoMaggiorazione, Long> {

}
