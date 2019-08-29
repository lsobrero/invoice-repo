package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.Ricezione;
import net.anet.invoice.repo.repository.RicezioneRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.Ricezione}.
 */
@RestController
@RequestMapping("/api")
public class RicezioneResource {

    private final Logger log = LoggerFactory.getLogger(RicezioneResource.class);

    private static final String ENTITY_NAME = "invoiceRicezione";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RicezioneRepository ricezioneRepository;

    public RicezioneResource(RicezioneRepository ricezioneRepository) {
        this.ricezioneRepository = ricezioneRepository;
    }

    /**
     * {@code POST  /riceziones} : Create a new ricezione.
     *
     * @param ricezione the ricezione to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ricezione, or with status {@code 400 (Bad Request)} if the ricezione has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/riceziones")
    public ResponseEntity<Ricezione> createRicezione(@Valid @RequestBody Ricezione ricezione) throws URISyntaxException {
        log.debug("REST request to save Ricezione : {}", ricezione);
        if (ricezione.getId() != null) {
            throw new BadRequestAlertException("A new ricezione cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Ricezione result = ricezioneRepository.save(ricezione);
        return ResponseEntity.created(new URI("/api/riceziones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /riceziones} : Updates an existing ricezione.
     *
     * @param ricezione the ricezione to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ricezione,
     * or with status {@code 400 (Bad Request)} if the ricezione is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ricezione couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/riceziones")
    public ResponseEntity<Ricezione> updateRicezione(@Valid @RequestBody Ricezione ricezione) throws URISyntaxException {
        log.debug("REST request to update Ricezione : {}", ricezione);
        if (ricezione.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Ricezione result = ricezioneRepository.save(ricezione);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ricezione.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /riceziones} : get all the riceziones.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riceziones in body.
     */
    @GetMapping("/riceziones")
    public List<Ricezione> getAllRiceziones() {
        log.debug("REST request to get all Riceziones");
        return ricezioneRepository.findAll();
    }

    /**
     * {@code GET  /riceziones/:id} : get the "id" ricezione.
     *
     * @param id the id of the ricezione to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ricezione, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/riceziones/{id}")
    public ResponseEntity<Ricezione> getRicezione(@PathVariable Long id) {
        log.debug("REST request to get Ricezione : {}", id);
        Optional<Ricezione> ricezione = ricezioneRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ricezione);
    }

    /**
     * {@code DELETE  /riceziones/:id} : delete the "id" ricezione.
     *
     * @param id the id of the ricezione to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/riceziones/{id}")
    public ResponseEntity<Void> deleteRicezione(@PathVariable Long id) {
        log.debug("REST request to delete Ricezione : {}", id);
        ricezioneRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
