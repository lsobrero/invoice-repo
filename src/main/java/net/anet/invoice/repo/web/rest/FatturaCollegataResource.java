package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.FatturaCollegata;
import net.anet.invoice.repo.repository.FatturaCollegataRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.FatturaCollegata}.
 */
@RestController
@RequestMapping("/api")
public class FatturaCollegataResource {

    private final Logger log = LoggerFactory.getLogger(FatturaCollegataResource.class);

    private static final String ENTITY_NAME = "invoiceFatturaCollegata";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FatturaCollegataRepository fatturaCollegataRepository;

    public FatturaCollegataResource(FatturaCollegataRepository fatturaCollegataRepository) {
        this.fatturaCollegataRepository = fatturaCollegataRepository;
    }

    /**
     * {@code POST  /fattura-collegatas} : Create a new fatturaCollegata.
     *
     * @param fatturaCollegata the fatturaCollegata to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fatturaCollegata, or with status {@code 400 (Bad Request)} if the fatturaCollegata has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fattura-collegatas")
    public ResponseEntity<FatturaCollegata> createFatturaCollegata(@Valid @RequestBody FatturaCollegata fatturaCollegata) throws URISyntaxException {
        log.debug("REST request to save FatturaCollegata : {}", fatturaCollegata);
        if (fatturaCollegata.getId() != null) {
            throw new BadRequestAlertException("A new fatturaCollegata cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FatturaCollegata result = fatturaCollegataRepository.save(fatturaCollegata);
        return ResponseEntity.created(new URI("/api/fattura-collegatas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fattura-collegatas} : Updates an existing fatturaCollegata.
     *
     * @param fatturaCollegata the fatturaCollegata to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fatturaCollegata,
     * or with status {@code 400 (Bad Request)} if the fatturaCollegata is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fatturaCollegata couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fattura-collegatas")
    public ResponseEntity<FatturaCollegata> updateFatturaCollegata(@Valid @RequestBody FatturaCollegata fatturaCollegata) throws URISyntaxException {
        log.debug("REST request to update FatturaCollegata : {}", fatturaCollegata);
        if (fatturaCollegata.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FatturaCollegata result = fatturaCollegataRepository.save(fatturaCollegata);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fatturaCollegata.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fattura-collegatas} : get all the fatturaCollegatas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fatturaCollegatas in body.
     */
    @GetMapping("/fattura-collegatas")
    public List<FatturaCollegata> getAllFatturaCollegatas() {
        log.debug("REST request to get all FatturaCollegatas");
        return fatturaCollegataRepository.findAll();
    }

    /**
     * {@code GET  /fattura-collegatas/:id} : get the "id" fatturaCollegata.
     *
     * @param id the id of the fatturaCollegata to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fatturaCollegata, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fattura-collegatas/{id}")
    public ResponseEntity<FatturaCollegata> getFatturaCollegata(@PathVariable Long id) {
        log.debug("REST request to get FatturaCollegata : {}", id);
        Optional<FatturaCollegata> fatturaCollegata = fatturaCollegataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fatturaCollegata);
    }

    /**
     * {@code DELETE  /fattura-collegatas/:id} : delete the "id" fatturaCollegata.
     *
     * @param id the id of the fatturaCollegata to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fattura-collegatas/{id}")
    public ResponseEntity<Void> deleteFatturaCollegata(@PathVariable Long id) {
        log.debug("REST request to delete FatturaCollegata : {}", id);
        fatturaCollegataRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
