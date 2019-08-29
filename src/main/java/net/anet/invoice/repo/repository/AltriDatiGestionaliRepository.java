package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.AltriDatiGestionali;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AltriDatiGestionali entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AltriDatiGestionaliRepository extends JpaRepository<AltriDatiGestionali, Long> {

}
