package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.DettaglioLinea;
import net.anet.invoice.repo.repository.DettaglioLineaRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.DettaglioLinea}.
 */
@RestController
@RequestMapping("/api")
public class DettaglioLineaResource {

    private final Logger log = LoggerFactory.getLogger(DettaglioLineaResource.class);

    private static final String ENTITY_NAME = "invoiceDettaglioLinea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DettaglioLineaRepository dettaglioLineaRepository;

    public DettaglioLineaResource(DettaglioLineaRepository dettaglioLineaRepository) {
        this.dettaglioLineaRepository = dettaglioLineaRepository;
    }

    /**
     * {@code POST  /dettaglio-lineas} : Create a new dettaglioLinea.
     *
     * @param dettaglioLinea the dettaglioLinea to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dettaglioLinea, or with status {@code 400 (Bad Request)} if the dettaglioLinea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dettaglio-lineas")
    public ResponseEntity<DettaglioLinea> createDettaglioLinea(@Valid @RequestBody DettaglioLinea dettaglioLinea) throws URISyntaxException {
        log.debug("REST request to save DettaglioLinea : {}", dettaglioLinea);
        if (dettaglioLinea.getId() != null) {
            throw new BadRequestAlertException("A new dettaglioLinea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DettaglioLinea result = dettaglioLineaRepository.save(dettaglioLinea);
        return ResponseEntity.created(new URI("/api/dettaglio-lineas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dettaglio-lineas} : Updates an existing dettaglioLinea.
     *
     * @param dettaglioLinea the dettaglioLinea to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dettaglioLinea,
     * or with status {@code 400 (Bad Request)} if the dettaglioLinea is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dettaglioLinea couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dettaglio-lineas")
    public ResponseEntity<DettaglioLinea> updateDettaglioLinea(@Valid @RequestBody DettaglioLinea dettaglioLinea) throws URISyntaxException {
        log.debug("REST request to update DettaglioLinea : {}", dettaglioLinea);
        if (dettaglioLinea.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DettaglioLinea result = dettaglioLineaRepository.save(dettaglioLinea);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dettaglioLinea.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dettaglio-lineas} : get all the dettaglioLineas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dettaglioLineas in body.
     */
    @GetMapping("/dettaglio-lineas")
    public List<DettaglioLinea> getAllDettaglioLineas() {
        log.debug("REST request to get all DettaglioLineas");
        return dettaglioLineaRepository.findAll();
    }

    /**
     * {@code GET  /dettaglio-lineas/:id} : get the "id" dettaglioLinea.
     *
     * @param id the id of the dettaglioLinea to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dettaglioLinea, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dettaglio-lineas/{id}")
    public ResponseEntity<DettaglioLinea> getDettaglioLinea(@PathVariable Long id) {
        log.debug("REST request to get DettaglioLinea : {}", id);
        Optional<DettaglioLinea> dettaglioLinea = dettaglioLineaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dettaglioLinea);
    }

    /**
     * {@code DELETE  /dettaglio-lineas/:id} : delete the "id" dettaglioLinea.
     *
     * @param id the id of the dettaglioLinea to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dettaglio-lineas/{id}")
    public ResponseEntity<Void> deleteDettaglioLinea(@PathVariable Long id) {
        log.debug("REST request to delete DettaglioLinea : {}", id);
        dettaglioLineaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
