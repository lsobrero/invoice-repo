package net.anet.invoice.repo.web.rest;

import net.anet.invoice.repo.domain.FAHeader;
import net.anet.invoice.repo.service.FAHeaderService;
import net.anet.invoice.repo.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link net.anet.invoice.repo.domain.FAHeader}.
 */
@RestController
@RequestMapping("/api")
public class FAHeaderResource {

    private final Logger log = LoggerFactory.getLogger(FAHeaderResource.class);

    private static final String ENTITY_NAME = "invoiceFaHeader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FAHeaderService fAHeaderService;

    public FAHeaderResource(FAHeaderService fAHeaderService) {
        this.fAHeaderService = fAHeaderService;
    }

    /**
     * {@code POST  /fa-headers} : Create a new fAHeader.
     *
     * @param fAHeader the fAHeader to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fAHeader, or with status {@code 400 (Bad Request)} if the fAHeader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fa-headers")
    public ResponseEntity<FAHeader> createFAHeader(@Valid @RequestBody FAHeader fAHeader) throws URISyntaxException {
        log.debug("REST request to save FAHeader : {}", fAHeader);
        if (fAHeader.getId() != null) {
            throw new BadRequestAlertException("A new fAHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FAHeader result = fAHeaderService.save(fAHeader);
        return ResponseEntity.created(new URI("/api/fa-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fa-headers} : Updates an existing fAHeader.
     *
     * @param fAHeader the fAHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fAHeader,
     * or with status {@code 400 (Bad Request)} if the fAHeader is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fAHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fa-headers")
    public ResponseEntity<FAHeader> updateFAHeader(@Valid @RequestBody FAHeader fAHeader) throws URISyntaxException {
        log.debug("REST request to update FAHeader : {}", fAHeader);
        if (fAHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FAHeader result = fAHeaderService.save(fAHeader);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fAHeader.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fa-headers} : get all the fAHeaders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fAHeaders in body.
     */
    @GetMapping("/fa-headers")
    public ResponseEntity<List<FAHeader>> getAllFAHeaders(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of FAHeaders");
        Page<FAHeader> page = fAHeaderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fa-headers/:id} : get the "id" fAHeader.
     *
     * @param id the id of the fAHeader to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fAHeader, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fa-headers/{id}")
    public ResponseEntity<FAHeader> getFAHeader(@PathVariable Long id) {
        log.debug("REST request to get FAHeader : {}", id);
        Optional<FAHeader> fAHeader = fAHeaderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fAHeader);
    }

    /**
     * {@code DELETE  /fa-headers/:id} : delete the "id" fAHeader.
     *
     * @param id the id of the fAHeader to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fa-headers/{id}")
    public ResponseEntity<Void> deleteFAHeader(@PathVariable Long id) {
        log.debug("REST request to delete FAHeader : {}", id);
        fAHeaderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
