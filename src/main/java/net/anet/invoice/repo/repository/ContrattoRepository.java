package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.Contratto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Contratto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContrattoRepository extends JpaRepository<Contratto, Long> {

}
