package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.DDT;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DDT entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DDTRepository extends JpaRepository<DDT, Long> {

}
