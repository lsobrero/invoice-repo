package net.anet.invoice.repo.repository;

import net.anet.invoice.repo.domain.DettaglioPagamento;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DettaglioPagamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DettaglioPagamentoRepository extends JpaRepository<DettaglioPagamento, Long> {

}
