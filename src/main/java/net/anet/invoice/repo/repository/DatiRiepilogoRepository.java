package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.DatiRiepilogo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DatiRiepilogo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DatiRiepilogoRepository extends JpaRepository<DatiRiepilogo, Long> {

}
