package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.DettaglioPagamento;
import net.anet.invoice.repo.repository.DettaglioPagamentoRepository;
import net.anet.invoice.repo.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link net.anet.invoice.repo.domain.DettaglioPagamento}.
 */
@RestController
@RequestMapping("/api")
public class DettaglioPagamentoResource {

    private final Logger log = LoggerFactory.getLogger(DettaglioPagamentoResource.class);

    private static final String ENTITY_NAME = "invoiceDettaglioPagamento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DettaglioPagamentoRepository dettaglioPagamentoRepository;

    public DettaglioPagamentoResource(DettaglioPagamentoRepository dettaglioPagamentoRepository) {
        this.dettaglioPagamentoRepository = dettaglioPagamentoRepository;
    }

    /**
     * {@code POST  /dettaglio-pagamentos} : Create a new dettaglioPagamento.
     *
     * @param dettaglioPagamento the dettaglioPagamento to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dettaglioPagamento, or with status {@code 400 (Bad Request)} if the dettaglioPagamento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dettaglio-pagamentos")
    public ResponseEntity<DettaglioPagamento> createDettaglioPagamento(@Valid @RequestBody DettaglioPagamento dettaglioPagamento) throws URISyntaxException {
        log.debug("REST request to save DettaglioPagamento : {}", dettaglioPagamento);
        if (dettaglioPagamento.getId() != null) {
            throw new BadRequestAlertException("A new dettaglioPagamento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DettaglioPagamento result = dettaglioPagamentoRepository.save(dettaglioPagamento);
        return ResponseEntity.created(new URI("/api/dettaglio-pagamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dettaglio-pagamentos} : Updates an existing dettaglioPagamento.
     *
     * @param dettaglioPagamento the dettaglioPagamento to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dettaglioPagamento,
     * or with status {@code 400 (Bad Request)} if the dettaglioPagamento is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dettaglioPagamento couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dettaglio-pagamentos")
    public ResponseEntity<DettaglioPagamento> updateDettaglioPagamento(@Valid @RequestBody DettaglioPagamento dettaglioPagamento) throws URISyntaxException {
        log.debug("REST request to update DettaglioPagamento : {}", dettaglioPagamento);
        if (dettaglioPagamento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DettaglioPagamento result = dettaglioPagamentoRepository.save(dettaglioPagamento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dettaglioPagamento.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dettaglio-pagamentos} : get all the dettaglioPagamentos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dettaglioPagamentos in body.
     */
    @GetMapping("/dettaglio-pagamentos")
    public List<DettaglioPagamento> getAllDettaglioPagamentos() {
        log.debug("REST request to get all DettaglioPagamentos");
        return dettaglioPagamentoRepository.findAll();
    }

    /**
     * {@code GET  /dettaglio-pagamentos/:id} : get the "id" dettaglioPagamento.
     *
     * @param id the id of the dettaglioPagamento to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dettaglioPagamento, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dettaglio-pagamentos/{id}")
    public ResponseEntity<DettaglioPagamento> getDettaglioPagamento(@PathVariable Long id) {
        log.debug("REST request to get DettaglioPagamento : {}", id);
        Optional<DettaglioPagamento> dettaglioPagamento = dettaglioPagamentoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dettaglioPagamento);
    }

    /**
     * {@code DELETE  /dettaglio-pagamentos/:id} : delete the "id" dettaglioPagamento.
     *
     * @param id the id of the dettaglioPagamento to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dettaglio-pagamentos/{id}")
    public ResponseEntity<Void> deleteDettaglioPagamento(@PathVariable Long id) {
        log.debug("REST request to delete DettaglioPagamento : {}", id);
        dettaglioPagamentoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
