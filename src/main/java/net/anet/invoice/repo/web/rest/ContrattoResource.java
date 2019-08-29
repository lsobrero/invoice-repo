package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.Contratto;
import net.anet.invoice.repo.repository.ContrattoRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.Contratto}.
 */
@RestController
@RequestMapping("/api")
public class ContrattoResource {

    private final Logger log = LoggerFactory.getLogger(ContrattoResource.class);

    private static final String ENTITY_NAME = "invoiceContratto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContrattoRepository contrattoRepository;

    public ContrattoResource(ContrattoRepository contrattoRepository) {
        this.contrattoRepository = contrattoRepository;
    }

    /**
     * {@code POST  /contrattos} : Create a new contratto.
     *
     * @param contratto the contratto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contratto, or with status {@code 400 (Bad Request)} if the contratto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contrattos")
    public ResponseEntity<Contratto> createContratto(@Valid @RequestBody Contratto contratto) throws URISyntaxException {
        log.debug("REST request to save Contratto : {}", contratto);
        if (contratto.getId() != null) {
            throw new BadRequestAlertException("A new contratto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Contratto result = contrattoRepository.save(contratto);
        return ResponseEntity.created(new URI("/api/contrattos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contrattos} : Updates an existing contratto.
     *
     * @param contratto the contratto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contratto,
     * or with status {@code 400 (Bad Request)} if the contratto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contratto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contrattos")
    public ResponseEntity<Contratto> updateContratto(@Valid @RequestBody Contratto contratto) throws URISyntaxException {
        log.debug("REST request to update Contratto : {}", contratto);
        if (contratto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Contratto result = contrattoRepository.save(contratto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contratto.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contrattos} : get all the contrattos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contrattos in body.
     */
    @GetMapping("/contrattos")
    public List<Contratto> getAllContrattos() {
        log.debug("REST request to get all Contrattos");
        return contrattoRepository.findAll();
    }

    /**
     * {@code GET  /contrattos/:id} : get the "id" contratto.
     *
     * @param id the id of the contratto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contratto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contrattos/{id}")
    public ResponseEntity<Contratto> getContratto(@PathVariable Long id) {
        log.debug("REST request to get Contratto : {}", id);
        Optional<Contratto> contratto = contrattoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(contratto);
    }

    /**
     * {@code DELETE  /contrattos/:id} : delete the "id" contratto.
     *
     * @param id the id of the contratto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contrattos/{id}")
    public ResponseEntity<Void> deleteContratto(@PathVariable Long id) {
        log.debug("REST request to delete Contratto : {}", id);
        contrattoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
