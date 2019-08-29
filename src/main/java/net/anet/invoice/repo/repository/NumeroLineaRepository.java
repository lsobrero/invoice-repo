package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.NumeroLinea;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NumeroLinea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NumeroLineaRepository extends JpaRepository<NumeroLinea, Long> {

}
