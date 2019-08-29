package net.anet.invoice.repo.service;

import net.anet.invoice.repo.domain.FAHeader;
import net.anet.invoice.repo.repository.FAHeaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FAHeader}.
 */
@Service
@Transactional
public class FAHeaderService {

    private final Logger log = LoggerFactory.getLogger(FAHeaderService.class);

    private final FAHeaderRepository fAHeaderRepository;

    public FAHeaderService(FAHeaderRepository fAHeaderRepository) {
        this.fAHeaderRepository = fAHeaderRepository;
    }

    /**
     * Save a fAHeader.
     *
     * @param fAHeader the entity to save.
     * @return the persisted entity.
     */
    public FAHeader save(FAHeader fAHeader) {
        log.debug("Request to save FAHeader : {}", fAHeader);
        return fAHeaderRepository.save(fAHeader);
    }

    /**
     * Get all the fAHeaders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FAHeader> findAll(Pageable pageable) {
        log.debug("Request to get all FAHeaders");
        return fAHeaderRepository.findAll(pageable);
    }


    /**
     * Get one fAHeader by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FAHeader> findOne(Long id) {
        log.debug("Request to get FAHeader : {}", id);
        return fAHeaderRepository.findById(id);
    }

    /**
     * Delete the fAHeader by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FAHeader : {}", id);
        fAHeaderRepository.deleteById(id);
    }
}
