package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.Convenzione;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Convenzione entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConvenzioneRepository extends JpaRepository<Convenzione, Long> {

}
