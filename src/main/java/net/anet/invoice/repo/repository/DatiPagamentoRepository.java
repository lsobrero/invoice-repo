package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.DatiPagamento;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DatiPagamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DatiPagamentoRepository extends JpaRepository<DatiPagamento, Long> {

}
