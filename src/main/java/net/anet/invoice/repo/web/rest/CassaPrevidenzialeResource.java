package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.CassaPrevidenziale;
import net.anet.invoice.repo.repository.CassaPrevidenzialeRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.CassaPrevidenziale}.
 */
@RestController
@RequestMapping("/api")
public class CassaPrevidenzialeResource {

    private final Logger log = LoggerFactory.getLogger(CassaPrevidenzialeResource.class);

    private static final String ENTITY_NAME = "invoiceCassaPrevidenziale";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CassaPrevidenzialeRepository cassaPrevidenzialeRepository;

    public CassaPrevidenzialeResource(CassaPrevidenzialeRepository cassaPrevidenzialeRepository) {
        this.cassaPrevidenzialeRepository = cassaPrevidenzialeRepository;
    }

    /**
     * {@code POST  /cassa-previdenziales} : Create a new cassaPrevidenziale.
     *
     * @param cassaPrevidenziale the cassaPrevidenziale to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cassaPrevidenziale, or with status {@code 400 (Bad Request)} if the cassaPrevidenziale has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cassa-previdenziales")
    public ResponseEntity<CassaPrevidenziale> createCassaPrevidenziale(@Valid @RequestBody CassaPrevidenziale cassaPrevidenziale) throws URISyntaxException {
        log.debug("REST request to save CassaPrevidenziale : {}", cassaPrevidenziale);
        if (cassaPrevidenziale.getId() != null) {
            throw new BadRequestAlertException("A new cassaPrevidenziale cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CassaPrevidenziale result = cassaPrevidenzialeRepository.save(cassaPrevidenziale);
        return ResponseEntity.created(new URI("/api/cassa-previdenziales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cassa-previdenziales} : Updates an existing cassaPrevidenziale.
     *
     * @param cassaPrevidenziale the cassaPrevidenziale to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cassaPrevidenziale,
     * or with status {@code 400 (Bad Request)} if the cassaPrevidenziale is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cassaPrevidenziale couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cassa-previdenziales")
    public ResponseEntity<CassaPrevidenziale> updateCassaPrevidenziale(@Valid @RequestBody CassaPrevidenziale cassaPrevidenziale) throws URISyntaxException {
        log.debug("REST request to update CassaPrevidenziale : {}", cassaPrevidenziale);
        if (cassaPrevidenziale.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CassaPrevidenziale result = cassaPrevidenzialeRepository.save(cassaPrevidenziale);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cassaPrevidenziale.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cassa-previdenziales} : get all the cassaPrevidenziales.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cassaPrevidenziales in body.
     */
    @GetMapping("/cassa-previdenziales")
    public List<CassaPrevidenziale> getAllCassaPrevidenziales() {
        log.debug("REST request to get all CassaPrevidenziales");
        return cassaPrevidenzialeRepository.findAll();
    }

    /**
     * {@code GET  /cassa-previdenziales/:id} : get the "id" cassaPrevidenziale.
     *
     * @param id the id of the cassaPrevidenziale to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cassaPrevidenziale, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cassa-previdenziales/{id}")
    public ResponseEntity<CassaPrevidenziale> getCassaPrevidenziale(@PathVariable Long id) {
        log.debug("REST request to get CassaPrevidenziale : {}", id);
        Optional<CassaPrevidenziale> cassaPrevidenziale = cassaPrevidenzialeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cassaPrevidenziale);
    }

    /**
     * {@code DELETE  /cassa-previdenziales/:id} : delete the "id" cassaPrevidenziale.
     *
     * @param id the id of the cassaPrevidenziale to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cassa-previdenziales/{id}")
    public ResponseEntity<Void> deleteCassaPrevidenziale(@PathVariable Long id) {
        log.debug("REST request to delete CassaPrevidenziale : {}", id);
        cassaPrevidenzialeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
