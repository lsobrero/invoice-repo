package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.Convenzione;
import net.anet.invoice.repo.repository.ConvenzioneRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.Convenzione}.
 */
@RestController
@RequestMapping("/api")
public class ConvenzioneResource {

    private final Logger log = LoggerFactory.getLogger(ConvenzioneResource.class);

    private static final String ENTITY_NAME = "invoiceConvenzione";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConvenzioneRepository convenzioneRepository;

    public ConvenzioneResource(ConvenzioneRepository convenzioneRepository) {
        this.convenzioneRepository = convenzioneRepository;
    }

    /**
     * {@code POST  /convenziones} : Create a new convenzione.
     *
     * @param convenzione the convenzione to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new convenzione, or with status {@code 400 (Bad Request)} if the convenzione has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/convenziones")
    public ResponseEntity<Convenzione> createConvenzione(@Valid @RequestBody Convenzione convenzione) throws URISyntaxException {
        log.debug("REST request to save Convenzione : {}", convenzione);
        if (convenzione.getId() != null) {
            throw new BadRequestAlertException("A new convenzione cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Convenzione result = convenzioneRepository.save(convenzione);
        return ResponseEntity.created(new URI("/api/convenziones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /convenziones} : Updates an existing convenzione.
     *
     * @param convenzione the convenzione to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated convenzione,
     * or with status {@code 400 (Bad Request)} if the convenzione is not valid,
     * or with status {@code 500 (Internal Server Error)} if the convenzione couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/convenziones")
    public ResponseEntity<Convenzione> updateConvenzione(@Valid @RequestBody Convenzione convenzione) throws URISyntaxException {
        log.debug("REST request to update Convenzione : {}", convenzione);
        if (convenzione.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Convenzione result = convenzioneRepository.save(convenzione);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, convenzione.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /convenziones} : get all the convenziones.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of convenziones in body.
     */
    @GetMapping("/convenziones")
    public List<Convenzione> getAllConvenziones() {
        log.debug("REST request to get all Convenziones");
        return convenzioneRepository.findAll();
    }

    /**
     * {@code GET  /convenziones/:id} : get the "id" convenzione.
     *
     * @param id the id of the convenzione to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the convenzione, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/convenziones/{id}")
    public ResponseEntity<Convenzione> getConvenzione(@PathVariable Long id) {
        log.debug("REST request to get Convenzione : {}", id);
        Optional<Convenzione> convenzione = convenzioneRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(convenzione);
    }

    /**
     * {@code DELETE  /convenziones/:id} : delete the "id" convenzione.
     *
     * @param id the id of the convenzione to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/convenziones/{id}")
    public ResponseEntity<Void> deleteConvenzione(@PathVariable Long id) {
        log.debug("REST request to delete Convenzione : {}", id);
        convenzioneRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
