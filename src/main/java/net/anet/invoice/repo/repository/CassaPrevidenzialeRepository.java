package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.CassaPrevidenziale;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CassaPrevidenziale entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CassaPrevidenzialeRepository extends JpaRepository<CassaPrevidenziale, Long> {

}
