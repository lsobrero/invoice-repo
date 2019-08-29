package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.Ricezione;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ricezione entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RicezioneRepository extends JpaRepository<Ricezione, Long> {

}
