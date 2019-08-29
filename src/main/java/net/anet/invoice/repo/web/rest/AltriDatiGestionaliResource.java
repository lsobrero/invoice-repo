package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.AltriDatiGestionali;
import net.anet.invoice.repo.repository.AltriDatiGestionaliRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.AltriDatiGestionali}.
 */
@RestController
@RequestMapping("/api")
public class AltriDatiGestionaliResource {

    private final Logger log = LoggerFactory.getLogger(AltriDatiGestionaliResource.class);

    private static final String ENTITY_NAME = "invoiceAltriDatiGestionali";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AltriDatiGestionaliRepository altriDatiGestionaliRepository;

    public AltriDatiGestionaliResource(AltriDatiGestionaliRepository altriDatiGestionaliRepository) {
        this.altriDatiGestionaliRepository = altriDatiGestionaliRepository;
    }

    /**
     * {@code POST  /altri-dati-gestionalis} : Create a new altriDatiGestionali.
     *
     * @param altriDatiGestionali the altriDatiGestionali to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new altriDatiGestionali, or with status {@code 400 (Bad Request)} if the altriDatiGestionali has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/altri-dati-gestionalis")
    public ResponseEntity<AltriDatiGestionali> createAltriDatiGestionali(@Valid @RequestBody AltriDatiGestionali altriDatiGestionali) throws URISyntaxException {
        log.debug("REST request to save AltriDatiGestionali : {}", altriDatiGestionali);
        if (altriDatiGestionali.getId() != null) {
            throw new BadRequestAlertException("A new altriDatiGestionali cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AltriDatiGestionali result = altriDatiGestionaliRepository.save(altriDatiGestionali);
        return ResponseEntity.created(new URI("/api/altri-dati-gestionalis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /altri-dati-gestionalis} : Updates an existing altriDatiGestionali.
     *
     * @param altriDatiGestionali the altriDatiGestionali to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated altriDatiGestionali,
     * or with status {@code 400 (Bad Request)} if the altriDatiGestionali is not valid,
     * or with status {@code 500 (Internal Server Error)} if the altriDatiGestionali couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/altri-dati-gestionalis")
    public ResponseEntity<AltriDatiGestionali> updateAltriDatiGestionali(@Valid @RequestBody AltriDatiGestionali altriDatiGestionali) throws URISyntaxException {
        log.debug("REST request to update AltriDatiGestionali : {}", altriDatiGestionali);
        if (altriDatiGestionali.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AltriDatiGestionali result = altriDatiGestionaliRepository.save(altriDatiGestionali);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, altriDatiGestionali.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /altri-dati-gestionalis} : get all the altriDatiGestionalis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of altriDatiGestionalis in body.
     */
    @GetMapping("/altri-dati-gestionalis")
    public List<AltriDatiGestionali> getAllAltriDatiGestionalis() {
        log.debug("REST request to get all AltriDatiGestionalis");
        return altriDatiGestionaliRepository.findAll();
    }

    /**
     * {@code GET  /altri-dati-gestionalis/:id} : get the "id" altriDatiGestionali.
     *
     * @param id the id of the altriDatiGestionali to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the altriDatiGestionali, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/altri-dati-gestionalis/{id}")
    public ResponseEntity<AltriDatiGestionali> getAltriDatiGestionali(@PathVariable Long id) {
        log.debug("REST request to get AltriDatiGestionali : {}", id);
        Optional<AltriDatiGestionali> altriDatiGestionali = altriDatiGestionaliRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(altriDatiGestionali);
    }

    /**
     * {@code DELETE  /altri-dati-gestionalis/:id} : delete the "id" altriDatiGestionali.
     *
     * @param id the id of the altriDatiGestionali to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/altri-dati-gestionalis/{id}")
    public ResponseEntity<Void> deleteAltriDatiGestionali(@PathVariable Long id) {
        log.debug("REST request to delete AltriDatiGestionali : {}", id);
        altriDatiGestionaliRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
