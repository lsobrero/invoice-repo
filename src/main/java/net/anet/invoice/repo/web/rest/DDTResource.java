package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.DDT;
import net.anet.invoice.repo.repository.DDTRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.DDT}.
 */
@RestController
@RequestMapping("/api")
public class DDTResource {

    private final Logger log = LoggerFactory.getLogger(DDTResource.class);

    private static final String ENTITY_NAME = "invoiceDdt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DDTRepository dDTRepository;

    public DDTResource(DDTRepository dDTRepository) {
        this.dDTRepository = dDTRepository;
    }

    /**
     * {@code POST  /ddts} : Create a new dDT.
     *
     * @param dDT the dDT to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dDT, or with status {@code 400 (Bad Request)} if the dDT has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ddts")
    public ResponseEntity<DDT> createDDT(@Valid @RequestBody DDT dDT) throws URISyntaxException {
        log.debug("REST request to save DDT : {}", dDT);
        if (dDT.getId() != null) {
            throw new BadRequestAlertException("A new dDT cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DDT result = dDTRepository.save(dDT);
        return ResponseEntity.created(new URI("/api/ddts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ddts} : Updates an existing dDT.
     *
     * @param dDT the dDT to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dDT,
     * or with status {@code 400 (Bad Request)} if the dDT is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dDT couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ddts")
    public ResponseEntity<DDT> updateDDT(@Valid @RequestBody DDT dDT) throws URISyntaxException {
        log.debug("REST request to update DDT : {}", dDT);
        if (dDT.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DDT result = dDTRepository.save(dDT);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dDT.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ddts} : get all the dDTS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dDTS in body.
     */
    @GetMapping("/ddts")
    public List<DDT> getAllDDTS() {
        log.debug("REST request to get all DDTS");
        return dDTRepository.findAll();
    }

    /**
     * {@code GET  /ddts/:id} : get the "id" dDT.
     *
     * @param id the id of the dDT to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dDT, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ddts/{id}")
    public ResponseEntity<DDT> getDDT(@PathVariable Long id) {
        log.debug("REST request to get DDT : {}", id);
        Optional<DDT> dDT = dDTRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dDT);
    }

    /**
     * {@code DELETE  /ddts/:id} : delete the "id" dDT.
     *
     * @param id the id of the dDT to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ddts/{id}")
    public ResponseEntity<Void> deleteDDT(@PathVariable Long id) {
        log.debug("REST request to delete DDT : {}", id);
        dDTRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
