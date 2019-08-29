package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.ScontoMaggiorazione;
import net.anet.invoice.repo.repository.ScontoMaggiorazioneRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.ScontoMaggiorazione}.
 */
@RestController
@RequestMapping("/api")
public class ScontoMaggiorazioneResource {

    private final Logger log = LoggerFactory.getLogger(ScontoMaggiorazioneResource.class);

    private static final String ENTITY_NAME = "invoiceScontoMaggiorazione";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ScontoMaggiorazioneRepository scontoMaggiorazioneRepository;

    public ScontoMaggiorazioneResource(ScontoMaggiorazioneRepository scontoMaggiorazioneRepository) {
        this.scontoMaggiorazioneRepository = scontoMaggiorazioneRepository;
    }

    /**
     * {@code POST  /sconto-maggioraziones} : Create a new scontoMaggiorazione.
     *
     * @param scontoMaggiorazione the scontoMaggiorazione to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scontoMaggiorazione, or with status {@code 400 (Bad Request)} if the scontoMaggiorazione has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sconto-maggioraziones")
    public ResponseEntity<ScontoMaggiorazione> createScontoMaggiorazione(@Valid @RequestBody ScontoMaggiorazione scontoMaggiorazione) throws URISyntaxException {
        log.debug("REST request to save ScontoMaggiorazione : {}", scontoMaggiorazione);
        if (scontoMaggiorazione.getId() != null) {
            throw new BadRequestAlertException("A new scontoMaggiorazione cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScontoMaggiorazione result = scontoMaggiorazioneRepository.save(scontoMaggiorazione);
        return ResponseEntity.created(new URI("/api/sconto-maggioraziones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sconto-maggioraziones} : Updates an existing scontoMaggiorazione.
     *
     * @param scontoMaggiorazione the scontoMaggiorazione to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scontoMaggiorazione,
     * or with status {@code 400 (Bad Request)} if the scontoMaggiorazione is not valid,
     * or with status {@code 500 (Internal Server Error)} if the scontoMaggiorazione couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sconto-maggioraziones")
    public ResponseEntity<ScontoMaggiorazione> updateScontoMaggiorazione(@Valid @RequestBody ScontoMaggiorazione scontoMaggiorazione) throws URISyntaxException {
        log.debug("REST request to update ScontoMaggiorazione : {}", scontoMaggiorazione);
        if (scontoMaggiorazione.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScontoMaggiorazione result = scontoMaggiorazioneRepository.save(scontoMaggiorazione);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, scontoMaggiorazione.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sconto-maggioraziones} : get all the scontoMaggioraziones.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of scontoMaggioraziones in body.
     */
    @GetMapping("/sconto-maggioraziones")
    public List<ScontoMaggiorazione> getAllScontoMaggioraziones() {
        log.debug("REST request to get all ScontoMaggioraziones");
        return scontoMaggiorazioneRepository.findAll();
    }

    /**
     * {@code GET  /sconto-maggioraziones/:id} : get the "id" scontoMaggiorazione.
     *
     * @param id the id of the scontoMaggiorazione to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the scontoMaggiorazione, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sconto-maggioraziones/{id}")
    public ResponseEntity<ScontoMaggiorazione> getScontoMaggiorazione(@PathVariable Long id) {
        log.debug("REST request to get ScontoMaggiorazione : {}", id);
        Optional<ScontoMaggiorazione> scontoMaggiorazione = scontoMaggiorazioneRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(scontoMaggiorazione);
    }

    /**
     * {@code DELETE  /sconto-maggioraziones/:id} : delete the "id" scontoMaggiorazione.
     *
     * @param id the id of the scontoMaggiorazione to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sconto-maggioraziones/{id}")
    public ResponseEntity<Void> deleteScontoMaggiorazione(@PathVariable Long id) {
        log.debug("REST request to delete ScontoMaggiorazione : {}", id);
        scontoMaggiorazioneRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
