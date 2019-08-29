package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.DatiRiepilogo;
import net.anet.invoice.repo.repository.DatiRiepilogoRepository;
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
 * REST controller for managing {@link net.anet.invoice.repo.domain.DatiRiepilogo}.
 */
@RestController
@RequestMapping("/api")
public class DatiRiepilogoResource {

    private final Logger log = LoggerFactory.getLogger(DatiRiepilogoResource.class);

    private static final String ENTITY_NAME = "invoiceDatiRiepilogo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DatiRiepilogoRepository datiRiepilogoRepository;

    public DatiRiepilogoResource(DatiRiepilogoRepository datiRiepilogoRepository) {
        this.datiRiepilogoRepository = datiRiepilogoRepository;
    }

    /**
     * {@code POST  /dati-riepilogos} : Create a new datiRiepilogo.
     *
     * @param datiRiepilogo the datiRiepilogo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new datiRiepilogo, or with status {@code 400 (Bad Request)} if the datiRiepilogo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dati-riepilogos")
    public ResponseEntity<DatiRiepilogo> createDatiRiepilogo(@Valid @RequestBody DatiRiepilogo datiRiepilogo) throws URISyntaxException {
        log.debug("REST request to save DatiRiepilogo : {}", datiRiepilogo);
        if (datiRiepilogo.getId() != null) {
            throw new BadRequestAlertException("A new datiRiepilogo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DatiRiepilogo result = datiRiepilogoRepository.save(datiRiepilogo);
        return ResponseEntity.created(new URI("/api/dati-riepilogos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dati-riepilogos} : Updates an existing datiRiepilogo.
     *
     * @param datiRiepilogo the datiRiepilogo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated datiRiepilogo,
     * or with status {@code 400 (Bad Request)} if the datiRiepilogo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the datiRiepilogo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dati-riepilogos")
    public ResponseEntity<DatiRiepilogo> updateDatiRiepilogo(@Valid @RequestBody DatiRiepilogo datiRiepilogo) throws URISyntaxException {
        log.debug("REST request to update DatiRiepilogo : {}", datiRiepilogo);
        if (datiRiepilogo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DatiRiepilogo result = datiRiepilogoRepository.save(datiRiepilogo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, datiRiepilogo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dati-riepilogos} : get all the datiRiepilogos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of datiRiepilogos in body.
     */
    @GetMapping("/dati-riepilogos")
    public List<DatiRiepilogo> getAllDatiRiepilogos() {
        log.debug("REST request to get all DatiRiepilogos");
        return datiRiepilogoRepository.findAll();
    }

    /**
     * {@code GET  /dati-riepilogos/:id} : get the "id" datiRiepilogo.
     *
     * @param id the id of the datiRiepilogo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the datiRiepilogo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dati-riepilogos/{id}")
    public ResponseEntity<DatiRiepilogo> getDatiRiepilogo(@PathVariable Long id) {
        log.debug("REST request to get DatiRiepilogo : {}", id);
        Optional<DatiRiepilogo> datiRiepilogo = datiRiepilogoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(datiRiepilogo);
    }

    /**
     * {@code DELETE  /dati-riepilogos/:id} : delete the "id" datiRiepilogo.
     *
     * @param id the id of the datiRiepilogo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dati-riepilogos/{id}")
    public ResponseEntity<Void> deleteDatiRiepilogo(@PathVariable Long id) {
        log.debug("REST request to delete DatiRiepilogo : {}", id);
        datiRiepilogoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
