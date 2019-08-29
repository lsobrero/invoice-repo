package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.OrdineAcquisto;
import net.anet.invoice.repo.repository.OrdineAcquistoRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.OrdineAcquisto}.
 */
@RestController
@RequestMapping("/api")
public class OrdineAcquistoResource {

    private final Logger log = LoggerFactory.getLogger(OrdineAcquistoResource.class);

    private static final String ENTITY_NAME = "invoiceOrdineAcquisto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrdineAcquistoRepository ordineAcquistoRepository;

    public OrdineAcquistoResource(OrdineAcquistoRepository ordineAcquistoRepository) {
        this.ordineAcquistoRepository = ordineAcquistoRepository;
    }

    /**
     * {@code POST  /ordine-acquistos} : Create a new ordineAcquisto.
     *
     * @param ordineAcquisto the ordineAcquisto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ordineAcquisto, or with status {@code 400 (Bad Request)} if the ordineAcquisto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ordine-acquistos")
    public ResponseEntity<OrdineAcquisto> createOrdineAcquisto(@Valid @RequestBody OrdineAcquisto ordineAcquisto) throws URISyntaxException {
        log.debug("REST request to save OrdineAcquisto : {}", ordineAcquisto);
        if (ordineAcquisto.getId() != null) {
            throw new BadRequestAlertException("A new ordineAcquisto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrdineAcquisto result = ordineAcquistoRepository.save(ordineAcquisto);
        return ResponseEntity.created(new URI("/api/ordine-acquistos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ordine-acquistos} : Updates an existing ordineAcquisto.
     *
     * @param ordineAcquisto the ordineAcquisto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ordineAcquisto,
     * or with status {@code 400 (Bad Request)} if the ordineAcquisto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ordineAcquisto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ordine-acquistos")
    public ResponseEntity<OrdineAcquisto> updateOrdineAcquisto(@Valid @RequestBody OrdineAcquisto ordineAcquisto) throws URISyntaxException {
        log.debug("REST request to update OrdineAcquisto : {}", ordineAcquisto);
        if (ordineAcquisto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrdineAcquisto result = ordineAcquistoRepository.save(ordineAcquisto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ordineAcquisto.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ordine-acquistos} : get all the ordineAcquistos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ordineAcquistos in body.
     */
    @GetMapping("/ordine-acquistos")
    public List<OrdineAcquisto> getAllOrdineAcquistos() {
        log.debug("REST request to get all OrdineAcquistos");
        return ordineAcquistoRepository.findAll();
    }

    /**
     * {@code GET  /ordine-acquistos/:id} : get the "id" ordineAcquisto.
     *
     * @param id the id of the ordineAcquisto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ordineAcquisto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ordine-acquistos/{id}")
    public ResponseEntity<OrdineAcquisto> getOrdineAcquisto(@PathVariable Long id) {
        log.debug("REST request to get OrdineAcquisto : {}", id);
        Optional<OrdineAcquisto> ordineAcquisto = ordineAcquistoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ordineAcquisto);
    }

    /**
     * {@code DELETE  /ordine-acquistos/:id} : delete the "id" ordineAcquisto.
     *
     * @param id the id of the ordineAcquisto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ordine-acquistos/{id}")
    public ResponseEntity<Void> deleteOrdineAcquisto(@PathVariable Long id) {
        log.debug("REST request to delete OrdineAcquisto : {}", id);
        ordineAcquistoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
