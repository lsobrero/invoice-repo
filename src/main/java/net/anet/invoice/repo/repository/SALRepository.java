package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.SAL;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SAL entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SALRepository extends JpaRepository<SAL, Long> {

}
