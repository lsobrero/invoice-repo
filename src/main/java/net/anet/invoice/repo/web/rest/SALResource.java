package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.SAL;
import net.anet.invoice.repo.repository.SALRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.SAL}.
 */
@RestController
@RequestMapping("/api")
public class SALResource {

    private final Logger log = LoggerFactory.getLogger(SALResource.class);

    private static final String ENTITY_NAME = "invoiceSal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SALRepository sALRepository;

    public SALResource(SALRepository sALRepository) {
        this.sALRepository = sALRepository;
    }

    /**
     * {@code POST  /sals} : Create a new sAL.
     *
     * @param sAL the sAL to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sAL, or with status {@code 400 (Bad Request)} if the sAL has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sals")
    public ResponseEntity<SAL> createSAL(@Valid @RequestBody SAL sAL) throws URISyntaxException {
        log.debug("REST request to save SAL : {}", sAL);
        if (sAL.getId() != null) {
            throw new BadRequestAlertException("A new sAL cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SAL result = sALRepository.save(sAL);
        return ResponseEntity.created(new URI("/api/sals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sals} : Updates an existing sAL.
     *
     * @param sAL the sAL to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sAL,
     * or with status {@code 400 (Bad Request)} if the sAL is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sAL couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sals")
    public ResponseEntity<SAL> updateSAL(@Valid @RequestBody SAL sAL) throws URISyntaxException {
        log.debug("REST request to update SAL : {}", sAL);
        if (sAL.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SAL result = sALRepository.save(sAL);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sAL.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sals} : get all the sALS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sALS in body.
     */
    @GetMapping("/sals")
    public List<SAL> getAllSALS() {
        log.debug("REST request to get all SALS");
        return sALRepository.findAll();
    }

    /**
     * {@code GET  /sals/:id} : get the "id" sAL.
     *
     * @param id the id of the sAL to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sAL, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sals/{id}")
    public ResponseEntity<SAL> getSAL(@PathVariable Long id) {
        log.debug("REST request to get SAL : {}", id);
        Optional<SAL> sAL = sALRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sAL);
    }

    /**
     * {@code DELETE  /sals/:id} : delete the "id" sAL.
     *
     * @param id the id of the sAL to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sals/{id}")
    public ResponseEntity<Void> deleteSAL(@PathVariable Long id) {
        log.debug("REST request to delete SAL : {}", id);
        sALRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
