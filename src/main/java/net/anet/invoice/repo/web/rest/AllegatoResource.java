package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.Allegato;
import net.anet.invoice.repo.repository.AllegatoRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.Allegato}.
 */
@RestController
@RequestMapping("/api")
public class AllegatoResource {

    private final Logger log = LoggerFactory.getLogger(AllegatoResource.class);

    private static final String ENTITY_NAME = "invoiceAllegato";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AllegatoRepository allegatoRepository;

    public AllegatoResource(AllegatoRepository allegatoRepository) {
        this.allegatoRepository = allegatoRepository;
    }

    /**
     * {@code POST  /allegatoes} : Create a new allegato.
     *
     * @param allegato the allegato to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new allegato, or with status {@code 400 (Bad Request)} if the allegato has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/allegatoes")
    public ResponseEntity<Allegato> createAllegato(@Valid @RequestBody Allegato allegato) throws URISyntaxException {
        log.debug("REST request to save Allegato : {}", allegato);
        if (allegato.getId() != null) {
            throw new BadRequestAlertException("A new allegato cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Allegato result = allegatoRepository.save(allegato);
        return ResponseEntity.created(new URI("/api/allegatoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /allegatoes} : Updates an existing allegato.
     *
     * @param allegato the allegato to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated allegato,
     * or with status {@code 400 (Bad Request)} if the allegato is not valid,
     * or with status {@code 500 (Internal Server Error)} if the allegato couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/allegatoes")
    public ResponseEntity<Allegato> updateAllegato(@Valid @RequestBody Allegato allegato) throws URISyntaxException {
        log.debug("REST request to update Allegato : {}", allegato);
        if (allegato.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Allegato result = allegatoRepository.save(allegato);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, allegato.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /allegatoes} : get all the allegatoes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of allegatoes in body.
     */
    @GetMapping("/allegatoes")
    public List<Allegato> getAllAllegatoes() {
        log.debug("REST request to get all Allegatoes");
        return allegatoRepository.findAll();
    }

    /**
     * {@code GET  /allegatoes/:id} : get the "id" allegato.
     *
     * @param id the id of the allegato to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the allegato, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/allegatoes/{id}")
    public ResponseEntity<Allegato> getAllegato(@PathVariable Long id) {
        log.debug("REST request to get Allegato : {}", id);
        Optional<Allegato> allegato = allegatoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(allegato);
    }

    /**
     * {@code DELETE  /allegatoes/:id} : delete the "id" allegato.
     *
     * @param id the id of the allegato to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/allegatoes/{id}")
    public ResponseEntity<Void> deleteAllegato(@PathVariable Long id) {
        log.debug("REST request to delete Allegato : {}", id);
        allegatoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
