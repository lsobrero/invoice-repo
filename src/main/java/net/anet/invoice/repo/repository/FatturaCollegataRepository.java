package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.FatturaCollegata;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FatturaCollegata entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FatturaCollegataRepository extends JpaRepository<FatturaCollegata, Long> {

}
