package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.DettaglioLinea;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DettaglioLinea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DettaglioLineaRepository extends JpaRepository<DettaglioLinea, Long> {

}
