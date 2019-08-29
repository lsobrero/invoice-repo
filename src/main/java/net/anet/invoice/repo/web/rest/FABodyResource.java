package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.FABody;
import net.anet.invoice.repo.repository.FABodyRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.FABody}.
 */
@RestController
@RequestMapping("/api")
public class FABodyResource {

    private final Logger log = LoggerFactory.getLogger(FABodyResource.class);

    private static final String ENTITY_NAME = "invoiceFaBody";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FABodyRepository fABodyRepository;

    public FABodyResource(FABodyRepository fABodyRepository) {
        this.fABodyRepository = fABodyRepository;
    }

    /**
     * {@code POST  /fa-bodies} : Create a new fABody.
     *
     * @param fABody the fABody to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fABody, or with status {@code 400 (Bad Request)} if the fABody has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fa-bodies")
    public ResponseEntity<FABody> createFABody(@Valid @RequestBody FABody fABody) throws URISyntaxException {
        log.debug("REST request to save FABody : {}", fABody);
        if (fABody.getId() != null) {
            throw new BadRequestAlertException("A new fABody cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FABody result = fABodyRepository.save(fABody);
        return ResponseEntity.created(new URI("/api/fa-bodies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fa-bodies} : Updates an existing fABody.
     *
     * @param fABody the fABody to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fABody,
     * or with status {@code 400 (Bad Request)} if the fABody is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fABody couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fa-bodies")
    public ResponseEntity<FABody> updateFABody(@Valid @RequestBody FABody fABody) throws URISyntaxException {
        log.debug("REST request to update FABody : {}", fABody);
        if (fABody.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FABody result = fABodyRepository.save(fABody);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fABody.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fa-bodies} : get all the fABodies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fABodies in body.
     */
    @GetMapping("/fa-bodies")
    public List<FABody> getAllFABodies() {
        log.debug("REST request to get all FABodies");
        return fABodyRepository.findAll();
    }

    /**
     * {@code GET  /fa-bodies/:id} : get the "id" fABody.
     *
     * @param id the id of the fABody to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fABody, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fa-bodies/{id}")
    public ResponseEntity<FABody> getFABody(@PathVariable Long id) {
        log.debug("REST request to get FABody : {}", id);
        Optional<FABody> fABody = fABodyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fABody);
    }

    /**
     * {@code DELETE  /fa-bodies/:id} : delete the "id" fABody.
     *
     * @param id the id of the fABody to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fa-bodies/{id}")
    public ResponseEntity<Void> deleteFABody(@PathVariable Long id) {
        log.debug("REST request to delete FABody : {}", id);
        fABodyRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
