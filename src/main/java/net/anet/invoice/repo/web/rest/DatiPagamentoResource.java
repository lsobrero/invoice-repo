package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.DatiPagamento;
import net.anet.invoice.repo.repository.DatiPagamentoRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.DatiPagamento}.
 */
@RestController
@RequestMapping("/api")
public class DatiPagamentoResource {

    private final Logger log = LoggerFactory.getLogger(DatiPagamentoResource.class);

    private static final String ENTITY_NAME = "invoiceDatiPagamento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DatiPagamentoRepository datiPagamentoRepository;

    public DatiPagamentoResource(DatiPagamentoRepository datiPagamentoRepository) {
        this.datiPagamentoRepository = datiPagamentoRepository;
    }

    /**
     * {@code POST  /dati-pagamentos} : Create a new datiPagamento.
     *
     * @param datiPagamento the datiPagamento to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new datiPagamento, or with status {@code 400 (Bad Request)} if the datiPagamento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dati-pagamentos")
    public ResponseEntity<DatiPagamento> createDatiPagamento(@Valid @RequestBody DatiPagamento datiPagamento) throws URISyntaxException {
        log.debug("REST request to save DatiPagamento : {}", datiPagamento);
        if (datiPagamento.getId() != null) {
            throw new BadRequestAlertException("A new datiPagamento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DatiPagamento result = datiPagamentoRepository.save(datiPagamento);
        return ResponseEntity.created(new URI("/api/dati-pagamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dati-pagamentos} : Updates an existing datiPagamento.
     *
     * @param datiPagamento the datiPagamento to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated datiPagamento,
     * or with status {@code 400 (Bad Request)} if the datiPagamento is not valid,
     * or with status {@code 500 (Internal Server Error)} if the datiPagamento couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dati-pagamentos")
    public ResponseEntity<DatiPagamento> updateDatiPagamento(@Valid @RequestBody DatiPagamento datiPagamento) throws URISyntaxException {
        log.debug("REST request to update DatiPagamento : {}", datiPagamento);
        if (datiPagamento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DatiPagamento result = datiPagamentoRepository.save(datiPagamento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, datiPagamento.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dati-pagamentos} : get all the datiPagamentos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of datiPagamentos in body.
     */
    @GetMapping("/dati-pagamentos")
    public List<DatiPagamento> getAllDatiPagamentos() {
        log.debug("REST request to get all DatiPagamentos");
        return datiPagamentoRepository.findAll();
    }

    /**
     * {@code GET  /dati-pagamentos/:id} : get the "id" datiPagamento.
     *
     * @param id the id of the datiPagamento to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the datiPagamento, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dati-pagamentos/{id}")
    public ResponseEntity<DatiPagamento> getDatiPagamento(@PathVariable Long id) {
        log.debug("REST request to get DatiPagamento : {}", id);
        Optional<DatiPagamento> datiPagamento = datiPagamentoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(datiPagamento);
    }

    /**
     * {@code DELETE  /dati-pagamentos/:id} : delete the "id" datiPagamento.
     *
     * @param id the id of the datiPagamento to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dati-pagamentos/{id}")
    public ResponseEntity<Void> deleteDatiPagamento(@PathVariable Long id) {
        log.debug("REST request to delete DatiPagamento : {}", id);
        datiPagamentoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
