package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.Causale;
import net.anet.invoice.repo.repository.CausaleRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.Causale}.
 */
@RestController
@RequestMapping("/api")
public class CausaleResource {

    private final Logger log = LoggerFactory.getLogger(CausaleResource.class);

    private static final String ENTITY_NAME = "invoiceCausale";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CausaleRepository causaleRepository;

    public CausaleResource(CausaleRepository causaleRepository) {
        this.causaleRepository = causaleRepository;
    }

    /**
     * {@code POST  /causales} : Create a new causale.
     *
     * @param causale the causale to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new causale, or with status {@code 400 (Bad Request)} if the causale has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/causales")
    public ResponseEntity<Causale> createCausale(@Valid @RequestBody Causale causale) throws URISyntaxException {
        log.debug("REST request to save Causale : {}", causale);
        if (causale.getId() != null) {
            throw new BadRequestAlertException("A new causale cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Causale result = causaleRepository.save(causale);
        return ResponseEntity.created(new URI("/api/causales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /causales} : Updates an existing causale.
     *
     * @param causale the causale to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated causale,
     * or with status {@code 400 (Bad Request)} if the causale is not valid,
     * or with status {@code 500 (Internal Server Error)} if the causale couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/causales")
    public ResponseEntity<Causale> updateCausale(@Valid @RequestBody Causale causale) throws URISyntaxException {
        log.debug("REST request to update Causale : {}", causale);
        if (causale.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Causale result = causaleRepository.save(causale);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, causale.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /causales} : get all the causales.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of causales in body.
     */
    @GetMapping("/causales")
    public List<Causale> getAllCausales() {
        log.debug("REST request to get all Causales");
        return causaleRepository.findAll();
    }

    /**
     * {@code GET  /causales/:id} : get the "id" causale.
     *
     * @param id the id of the causale to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the causale, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/causales/{id}")
    public ResponseEntity<Causale> getCausale(@PathVariable Long id) {
        log.debug("REST request to get Causale : {}", id);
        Optional<Causale> causale = causaleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(causale);
    }

    /**
     * {@code DELETE  /causales/:id} : delete the "id" causale.
     *
     * @param id the id of the causale to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/causales/{id}")
    public ResponseEntity<Void> deleteCausale(@PathVariable Long id) {
        log.debug("REST request to delete Causale : {}", id);
        causaleRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
