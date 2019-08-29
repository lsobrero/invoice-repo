package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.NumeroLinea;
import net.anet.invoice.repo.repository.NumeroLineaRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.NumeroLinea}.
 */
@RestController
@RequestMapping("/api")
public class NumeroLineaResource {

    private final Logger log = LoggerFactory.getLogger(NumeroLineaResource.class);

    private static final String ENTITY_NAME = "invoiceNumeroLinea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NumeroLineaRepository numeroLineaRepository;

    public NumeroLineaResource(NumeroLineaRepository numeroLineaRepository) {
        this.numeroLineaRepository = numeroLineaRepository;
    }

    /**
     * {@code POST  /numero-lineas} : Create a new numeroLinea.
     *
     * @param numeroLinea the numeroLinea to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new numeroLinea, or with status {@code 400 (Bad Request)} if the numeroLinea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/numero-lineas")
    public ResponseEntity<NumeroLinea> createNumeroLinea(@Valid @RequestBody NumeroLinea numeroLinea) throws URISyntaxException {
        log.debug("REST request to save NumeroLinea : {}", numeroLinea);
        if (numeroLinea.getId() != null) {
            throw new BadRequestAlertException("A new numeroLinea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NumeroLinea result = numeroLineaRepository.save(numeroLinea);
        return ResponseEntity.created(new URI("/api/numero-lineas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /numero-lineas} : Updates an existing numeroLinea.
     *
     * @param numeroLinea the numeroLinea to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated numeroLinea,
     * or with status {@code 400 (Bad Request)} if the numeroLinea is not valid,
     * or with status {@code 500 (Internal Server Error)} if the numeroLinea couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/numero-lineas")
    public ResponseEntity<NumeroLinea> updateNumeroLinea(@Valid @RequestBody NumeroLinea numeroLinea) throws URISyntaxException {
        log.debug("REST request to update NumeroLinea : {}", numeroLinea);
        if (numeroLinea.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NumeroLinea result = numeroLineaRepository.save(numeroLinea);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, numeroLinea.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /numero-lineas} : get all the numeroLineas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of numeroLineas in body.
     */
    @GetMapping("/numero-lineas")
    public List<NumeroLinea> getAllNumeroLineas() {
        log.debug("REST request to get all NumeroLineas");
        return numeroLineaRepository.findAll();
    }

    /**
     * {@code GET  /numero-lineas/:id} : get the "id" numeroLinea.
     *
     * @param id the id of the numeroLinea to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the numeroLinea, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/numero-lineas/{id}")
    public ResponseEntity<NumeroLinea> getNumeroLinea(@PathVariable Long id) {
        log.debug("REST request to get NumeroLinea : {}", id);
        Optional<NumeroLinea> numeroLinea = numeroLineaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(numeroLinea);
    }

    /**
     * {@code DELETE  /numero-lineas/:id} : delete the "id" numeroLinea.
     *
     * @param id the id of the numeroLinea to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/numero-lineas/{id}")
    public ResponseEntity<Void> deleteNumeroLinea(@PathVariable Long id) {
        log.debug("REST request to delete NumeroLinea : {}", id);
        numeroLineaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
