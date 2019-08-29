package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.CodiceArticolo;
import net.anet.invoice.repo.repository.CodiceArticoloRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.CodiceArticolo}.
 */
@RestController
@RequestMapping("/api")
public class CodiceArticoloResource {

    private final Logger log = LoggerFactory.getLogger(CodiceArticoloResource.class);

    private static final String ENTITY_NAME = "invoiceCodiceArticolo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CodiceArticoloRepository codiceArticoloRepository;

    public CodiceArticoloResource(CodiceArticoloRepository codiceArticoloRepository) {
        this.codiceArticoloRepository = codiceArticoloRepository;
    }

    /**
     * {@code POST  /codice-articolos} : Create a new codiceArticolo.
     *
     * @param codiceArticolo the codiceArticolo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new codiceArticolo, or with status {@code 400 (Bad Request)} if the codiceArticolo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/codice-articolos")
    public ResponseEntity<CodiceArticolo> createCodiceArticolo(@Valid @RequestBody CodiceArticolo codiceArticolo) throws URISyntaxException {
        log.debug("REST request to save CodiceArticolo : {}", codiceArticolo);
        if (codiceArticolo.getId() != null) {
            throw new BadRequestAlertException("A new codiceArticolo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CodiceArticolo result = codiceArticoloRepository.save(codiceArticolo);
        return ResponseEntity.created(new URI("/api/codice-articolos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /codice-articolos} : Updates an existing codiceArticolo.
     *
     * @param codiceArticolo the codiceArticolo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated codiceArticolo,
     * or with status {@code 400 (Bad Request)} if the codiceArticolo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the codiceArticolo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/codice-articolos")
    public ResponseEntity<CodiceArticolo> updateCodiceArticolo(@Valid @RequestBody CodiceArticolo codiceArticolo) throws URISyntaxException {
        log.debug("REST request to update CodiceArticolo : {}", codiceArticolo);
        if (codiceArticolo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CodiceArticolo result = codiceArticoloRepository.save(codiceArticolo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, codiceArticolo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /codice-articolos} : get all the codiceArticolos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of codiceArticolos in body.
     */
    @GetMapping("/codice-articolos")
    public List<CodiceArticolo> getAllCodiceArticolos() {
        log.debug("REST request to get all CodiceArticolos");
        return codiceArticoloRepository.findAll();
    }

    /**
     * {@code GET  /codice-articolos/:id} : get the "id" codiceArticolo.
     *
     * @param id the id of the codiceArticolo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the codiceArticolo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/codice-articolos/{id}")
    public ResponseEntity<CodiceArticolo> getCodiceArticolo(@PathVariable Long id) {
        log.debug("REST request to get CodiceArticolo : {}", id);
        Optional<CodiceArticolo> codiceArticolo = codiceArticoloRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(codiceArticolo);
    }

    /**
     * {@code DELETE  /codice-articolos/:id} : delete the "id" codiceArticolo.
     *
     * @param id the id of the codiceArticolo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/codice-articolos/{id}")
    public ResponseEntity<Void> deleteCodiceArticolo(@PathVariable Long id) {
        log.debug("REST request to delete CodiceArticolo : {}", id);
        codiceArticoloRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
