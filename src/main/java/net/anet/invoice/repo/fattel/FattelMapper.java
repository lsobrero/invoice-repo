package net.anet.invoice.repo.fattel;

import it.olivetti.hdo.dispatcher.models.pa.*;
import net.anet.invoice.repo.domain.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FattelMapper {

    public static FAHeader mapToHeader(FatturaElettronicaType fe){
        FAHeader fAHeader = new FAHeader();

        FatturaElettronicaHeaderType header = fe.getFatturaElettronicaHeader();
        fAHeader.setTrasmittenteIdCodice(header.getDatiTrasmissione().getIdTrasmittente().getIdCodice().trim());
        fAHeader.setTrasmittenteIdPaese(header.getDatiTrasmissione().getIdTrasmittente().getIdPaese().trim());
        fAHeader.setProgressivoInvio(header.getDatiTrasmissione().getProgressivoInvio().trim());

        fAHeader.setFormatoTrasmissione(header.getDatiTrasmissione().getFormatoTrasmissione().value()!=null?header.getDatiTrasmissione().getFormatoTrasmissione().value().trim():null);
        fAHeader.setCodiceDestinatario(header.getDatiTrasmissione().getCodiceDestinatario().trim());

        if(header.getDatiTrasmissione().getContattiTrasmittente() != null){
            fAHeader.setTelefono(header.getDatiTrasmissione().getContattiTrasmittente().getTelefono());
            fAHeader.setEmail(header.getDatiTrasmissione().getContattiTrasmittente().getEmail());
        }

        fAHeader.setpECDestinatario(header.getDatiTrasmissione().getPECDestinatario());

        /**
         * Cedente Prestatore - Dati Anagrafici
         */
        if(header.getCedentePrestatore() != null){
            if(header.getCedentePrestatore().getDatiAnagrafici() != null) {
                fAHeader.setcPDACodiceFiscale(header.getCedentePrestatore().getDatiAnagrafici().getCodiceFiscale()!=null?header.getCedentePrestatore().getDatiAnagrafici().getCodiceFiscale().trim():null);
                fAHeader.setcPAAlboProfessionale(header.getCedentePrestatore().getDatiAnagrafici().getAlboProfessionale()!=null?header.getCedentePrestatore().getDatiAnagrafici().getAlboProfessionale().trim():null);
                fAHeader.setcPAProvinciaAlbo(header.getCedentePrestatore().getDatiAnagrafici().getProvinciaAlbo()!=null?header.getCedentePrestatore().getDatiAnagrafici().getProvinciaAlbo().trim():null);
                fAHeader.setcPANumeroIscrizioneAlbo(header.getCedentePrestatore().getDatiAnagrafici().getNumeroIscrizioneAlbo()!=null?header.getCedentePrestatore().getDatiAnagrafici().getNumeroIscrizioneAlbo().trim():null);
                fAHeader.setcPADataIscrizioneAlbo(header.getCedentePrestatore().getDatiAnagrafici().getDataIscrizioneAlbo()!=null?header.getCedentePrestatore().getDatiAnagrafici().getDataIscrizioneAlbo().toString():null);
                fAHeader.setcPARegimeFiscale(header.getCedentePrestatore().getDatiAnagrafici().getRegimeFiscale()!=null?header.getCedentePrestatore().getDatiAnagrafici().getRegimeFiscale().value().trim():null);

                if (header.getCedentePrestatore().getDatiAnagrafici().getIdFiscaleIVA() != null) {
                    fAHeader.setcPDAIdPaese(header.getCedentePrestatore().getDatiAnagrafici().getIdFiscaleIVA().getIdPaese()!=null?header.getCedentePrestatore().getDatiAnagrafici().getIdFiscaleIVA().getIdPaese().trim():null);
                    fAHeader.setcPDAIdCodice(header.getCedentePrestatore().getDatiAnagrafici().getIdFiscaleIVA().getIdCodice()!=null?header.getCedentePrestatore().getDatiAnagrafici().getIdFiscaleIVA().getIdCodice().trim():null);
                }

                if (header.getCedentePrestatore().getDatiAnagrafici().getAnagrafica() != null) {
                    fAHeader.setcPADenominazione(header.getCedentePrestatore().getDatiAnagrafici().getAnagrafica().getDenominazione()!=null?header.getCedentePrestatore().getDatiAnagrafici().getAnagrafica().getDenominazione().trim():null);
                    fAHeader.setcPANome(header.getCedentePrestatore().getDatiAnagrafici().getAnagrafica().getNome()!=null?header.getCedentePrestatore().getDatiAnagrafici().getAnagrafica().getNome().trim():null);
                    fAHeader.setcPACognome(header.getCedentePrestatore().getDatiAnagrafici().getAnagrafica().getCognome()!=null?header.getCedentePrestatore().getDatiAnagrafici().getAnagrafica().getCognome().trim():null);
                    fAHeader.setcPATitolo(header.getCedentePrestatore().getDatiAnagrafici().getAnagrafica().getTitolo()!=null?header.getCedentePrestatore().getDatiAnagrafici().getAnagrafica().getTitolo().trim():null);
                    fAHeader.setcPACodEORI(header.getCedentePrestatore().getDatiAnagrafici().getAnagrafica().getCodEORI()!=null?header.getCedentePrestatore().getDatiAnagrafici().getAnagrafica().getCodEORI().trim():null);
                }

                if (header.getCedentePrestatore().getSede() != null) {
                    /**
                     * Cedente Prestatore - Sede
                     */
                    fAHeader.setcCSIndirizzo(header.getCedentePrestatore().getSede().getIndirizzo()!=null?header.getCedentePrestatore().getSede().getIndirizzo().trim():null);
                    fAHeader.setcCSNumeroCivico(header.getCedentePrestatore().getSede().getNumeroCivico()!=null?header.getCedentePrestatore().getSede().getNumeroCivico().trim():null);
                    fAHeader.setcCSCAP(header.getCedentePrestatore().getSede().getCAP()!=null?header.getCedentePrestatore().getSede().getCAP().trim():null);
                    fAHeader.setcCSComune(header.getCedentePrestatore().getSede().getComune()!=null?header.getCedentePrestatore().getSede().getComune().trim():null);
                    fAHeader.setcCSProvincia(header.getCedentePrestatore().getSede().getProvincia()!=null?header.getCedentePrestatore().getSede().getProvincia().trim():null);
                    fAHeader.setcCSNazione(header.getCedentePrestatore().getSede().getNazione()!=null?header.getCedentePrestatore().getSede().getNazione().trim():null);
                }

                if (header.getCedentePrestatore().getStabileOrganizzazione() != null) {
                    /**
                     * Cedente Prestatore - StabileOrganizzazione
                     */
                    fAHeader.setsOIndirizzo(header.getCedentePrestatore().getStabileOrganizzazione().getIndirizzo()!=null?header.getCedentePrestatore().getStabileOrganizzazione().getIndirizzo().trim():null);
                    fAHeader.setsONumeroCivico(header.getCedentePrestatore().getStabileOrganizzazione().getNumeroCivico()!=null?header.getCedentePrestatore().getStabileOrganizzazione().getNumeroCivico().trim():null);
                    fAHeader.setsOCAP(header.getCedentePrestatore().getStabileOrganizzazione().getCAP()!=null?header.getCedentePrestatore().getStabileOrganizzazione().getCAP().trim():null);
                    fAHeader.setsOComune(header.getCedentePrestatore().getStabileOrganizzazione().getComune()!=null?header.getCedentePrestatore().getStabileOrganizzazione().getComune().trim():null);
                    fAHeader.setsOProvincia(header.getCedentePrestatore().getStabileOrganizzazione().getProvincia()!=null?header.getCedentePrestatore().getStabileOrganizzazione().getProvincia().trim():null);
                    fAHeader.setsONazione(header.getCedentePrestatore().getStabileOrganizzazione().getNazione()!=null?header.getCedentePrestatore().getStabileOrganizzazione().getNazione().trim():null);
                }
                if (header.getCedentePrestatore().getIscrizioneREA() != null) {
                    /**
                     * Cedente Prestatore - Iscrizione REA
                     */
                    fAHeader.setsOIREAUfficio(header.getCedentePrestatore().getIscrizioneREA().getUfficio()!=null?header.getCedentePrestatore().getIscrizioneREA().getUfficio().trim():null);
                    fAHeader.setsOIREANumeroREA(header.getCedentePrestatore().getIscrizioneREA().getNumeroREA()!=null?header.getCedentePrestatore().getIscrizioneREA().getNumeroREA().trim():null);
                    fAHeader.setsOIREACapitaleSociale(header.getCedentePrestatore().getIscrizioneREA().getCapitaleSociale()!=null?header.getCedentePrestatore().getIscrizioneREA().getCapitaleSociale().toString():null);
                    fAHeader.setsOIREASocioUnico(header.getCedentePrestatore().getIscrizioneREA().getSocioUnico()!=null?header.getCedentePrestatore().getIscrizioneREA().getSocioUnico().value().trim():null);
                    fAHeader.setsOIREAStatoLiquidazione(header.getCedentePrestatore().getIscrizioneREA().getStatoLiquidazione()!=null?header.getCedentePrestatore().getIscrizioneREA().getStatoLiquidazione().value():null);
                }

                if (header.getCedentePrestatore().getContatti() != null) {
                    /**
                     * Cedente Prestatore - Contatti
                     */
                    fAHeader.setsOCTelefono(header.getCedentePrestatore().getContatti().getTelefono()!=null?header.getCedentePrestatore().getContatti().getTelefono().trim():null);
                    fAHeader.setsOCFax(header.getCedentePrestatore().getContatti().getFax()!=null?header.getCedentePrestatore().getContatti().getFax().trim():null);
                    fAHeader.setsOCEmail(header.getCedentePrestatore().getContatti().getEmail()!=null?header.getCedentePrestatore().getContatti().getEmail().trim():null);
                }
                fAHeader.setsOCRiferimentoAmministrazione(header.getCedentePrestatore().getRiferimentoAmministrazione()!=null?header.getCedentePrestatore().getRiferimentoAmministrazione().trim():null);

            }

            /**
             * Rappresentante Fiscale
             */
            if(header.getRappresentanteFiscale() != null){
                /**
                 * Rappresentante Fiscale - Dati Anagrafici
                 */
                if(header.getRappresentanteFiscale().getDatiAnagrafici() != null){
                    if(header.getRappresentanteFiscale().getDatiAnagrafici().getIdFiscaleIVA() != null){
                        fAHeader.setrFDAIdPaese(header.getRappresentanteFiscale().getDatiAnagrafici().getIdFiscaleIVA().getIdPaese()!=null?header.getRappresentanteFiscale().getDatiAnagrafici().getIdFiscaleIVA().getIdPaese().trim():null);
                        fAHeader.setrFDAIdCodice(header.getRappresentanteFiscale().getDatiAnagrafici().getIdFiscaleIVA().getIdCodice()!=null?header.getRappresentanteFiscale().getDatiAnagrafici().getIdFiscaleIVA().getIdCodice().trim():null);
                    }
                    fAHeader.setrFDACodiceFiscale(header.getRappresentanteFiscale().getDatiAnagrafici().getCodiceFiscale()!=null?header.getRappresentanteFiscale().getDatiAnagrafici().getCodiceFiscale().trim():null);
                    fAHeader.setrFADenominazione(header.getRappresentanteFiscale().getDatiAnagrafici().getAnagrafica().getDenominazione()!=null?header.getRappresentanteFiscale().getDatiAnagrafici().getAnagrafica().getDenominazione().trim():null);
                    fAHeader.setrFANome(header.getRappresentanteFiscale().getDatiAnagrafici().getAnagrafica().getNome()!=null?header.getRappresentanteFiscale().getDatiAnagrafici().getAnagrafica().getNome().trim():null);
                    fAHeader.setrFACognome(header.getRappresentanteFiscale().getDatiAnagrafici().getAnagrafica().getCognome()!=null?header.getRappresentanteFiscale().getDatiAnagrafici().getAnagrafica().getCognome().trim():null);
                    fAHeader.setrFATitolo(header.getRappresentanteFiscale().getDatiAnagrafici().getAnagrafica().getTitolo()!=null?header.getRappresentanteFiscale().getDatiAnagrafici().getAnagrafica().getTitolo().trim():null);
                    fAHeader.setrFACodEORI(header.getRappresentanteFiscale().getDatiAnagrafici().getAnagrafica().getCodEORI()!=null?header.getRappresentanteFiscale().getDatiAnagrafici().getAnagrafica().getCodEORI().trim():null);
                }

            }


            /**
             * Cessionario Committente
             */
            if(header.getCessionarioCommittente() != null){
                /**
                 * Cessionario Committente - Dati Anagrafici
                 */
                if(header.getCessionarioCommittente().getDatiAnagrafici() != null){
                    if(header.getCessionarioCommittente().getDatiAnagrafici().getIdFiscaleIVA() != null){
                        fAHeader.setcCDAIdPaese(header.getCessionarioCommittente().getDatiAnagrafici().getIdFiscaleIVA().getIdPaese()!=null?header.getCessionarioCommittente().getDatiAnagrafici().getIdFiscaleIVA().getIdPaese().trim():null);
                        fAHeader.setcCDAIdCodice(header.getCessionarioCommittente().getDatiAnagrafici().getIdFiscaleIVA().getIdCodice()!=null?header.getCessionarioCommittente().getDatiAnagrafici().getIdFiscaleIVA().getIdCodice().trim():null);
                    }
                    fAHeader.setcCDACodiceFiscale(header.getCessionarioCommittente().getDatiAnagrafici().getCodiceFiscale()!=null?header.getCessionarioCommittente().getDatiAnagrafici().getCodiceFiscale().trim():null);

                    if(header.getCessionarioCommittente().getDatiAnagrafici().getAnagrafica() != null){
                        fAHeader.setcCADenominazione(header.getCessionarioCommittente().getDatiAnagrafici().getAnagrafica().getDenominazione()!=null?header.getCessionarioCommittente().getDatiAnagrafici().getAnagrafica().getDenominazione().trim():null);
                        fAHeader.setcCANome(header.getCessionarioCommittente().getDatiAnagrafici().getAnagrafica().getNome()!=null?header.getCessionarioCommittente().getDatiAnagrafici().getAnagrafica().getNome().trim():null);
                        fAHeader.setcCACognome(header.getCessionarioCommittente().getDatiAnagrafici().getAnagrafica().getCognome()!=null?header.getCessionarioCommittente().getDatiAnagrafici().getAnagrafica().getCognome().trim():null);
                        fAHeader.setcCATitolo(header.getCessionarioCommittente().getDatiAnagrafici().getAnagrafica().getTitolo()!=null?header.getCessionarioCommittente().getDatiAnagrafici().getAnagrafica().getTitolo().trim():null);
                        fAHeader.setcCACodEORI(header.getCessionarioCommittente().getDatiAnagrafici().getAnagrafica().getCodEORI()!=null?header.getCessionarioCommittente().getDatiAnagrafici().getAnagrafica().getCodEORI().trim():null);
                    }
                    if(header.getCessionarioCommittente().getSede() != null){
                        fAHeader.setcCSIndirizzo(header.getCessionarioCommittente().getSede().getIndirizzo()!=null?header.getCessionarioCommittente().getSede().getIndirizzo().trim():null);
                        fAHeader.setcCSNumeroCivico(header.getCessionarioCommittente().getSede().getNumeroCivico()!=null?header.getCessionarioCommittente().getSede().getNumeroCivico().trim():null);
                        fAHeader.setcCSCAP(header.getCessionarioCommittente().getSede().getCAP()!=null?header.getCessionarioCommittente().getSede().getCAP().trim():null);
                        fAHeader.setcCSComune(header.getCessionarioCommittente().getSede().getComune()!=null?header.getCessionarioCommittente().getSede().getComune().trim():null);
                        fAHeader.setcCSProvincia(header.getCessionarioCommittente().getSede().getProvincia()!=null?header.getCessionarioCommittente().getSede().getProvincia().trim():null);
                        fAHeader.setcCSNazione(header.getCessionarioCommittente().getSede().getNazione()!=null?header.getCessionarioCommittente().getSede().getNazione().trim():null);
                    }
                    if(header.getCessionarioCommittente().getStabileOrganizzazione() != null){
                        fAHeader.setcCSOIndirizzo(header.getCessionarioCommittente().getStabileOrganizzazione().getIndirizzo()!=null?header.getCessionarioCommittente().getStabileOrganizzazione().getIndirizzo().trim():null);
                        fAHeader.setcCSONumeroCivico(header.getCessionarioCommittente().getStabileOrganizzazione().getNumeroCivico()!=null?header.getCessionarioCommittente().getStabileOrganizzazione().getNumeroCivico().trim():null);
                        fAHeader.setcCSOCAP(header.getCessionarioCommittente().getStabileOrganizzazione().getCAP()!=null?header.getCessionarioCommittente().getStabileOrganizzazione().getCAP().trim():null);
                        fAHeader.setcCSOComune(header.getCessionarioCommittente().getStabileOrganizzazione().getComune()!=null?header.getCessionarioCommittente().getStabileOrganizzazione().getComune().trim():null);
                        fAHeader.setcCSOProvincia(header.getCessionarioCommittente().getStabileOrganizzazione().getProvincia()!=null?header.getCessionarioCommittente().getStabileOrganizzazione().getProvincia().trim():null);
                        fAHeader.setcCSONazione(header.getCessionarioCommittente().getStabileOrganizzazione().getNazione()!=null?header.getCessionarioCommittente().getStabileOrganizzazione().getNazione().trim():null);
                    }
                    if(header.getCessionarioCommittente().getRappresentanteFiscale() != null){
                        if(header.getCessionarioCommittente().getRappresentanteFiscale().getIdFiscaleIVA() != null){
                            fAHeader.setcCRFIdCodice(header.getCessionarioCommittente().getRappresentanteFiscale().getIdFiscaleIVA().getIdCodice()!=null?header.getCessionarioCommittente().getRappresentanteFiscale().getIdFiscaleIVA().getIdCodice().trim():null);
                            fAHeader.setcCRFIdPaese(header.getCessionarioCommittente().getRappresentanteFiscale().getIdFiscaleIVA().getIdPaese()!=null?header.getCessionarioCommittente().getRappresentanteFiscale().getIdFiscaleIVA().getIdPaese().trim():null);
                        }
                        fAHeader.setcCRFDenominazione(header.getCessionarioCommittente().getRappresentanteFiscale().getDenominazione()!=null?header.getCessionarioCommittente().getRappresentanteFiscale().getDenominazione().trim():null);
                        fAHeader.setcCRFNome(header.getCessionarioCommittente().getRappresentanteFiscale().getNome()!=null?header.getCessionarioCommittente().getRappresentanteFiscale().getNome().trim():null);
                        fAHeader.setcCRFCognome(header.getCessionarioCommittente().getRappresentanteFiscale().getCognome()!=null?header.getCessionarioCommittente().getRappresentanteFiscale().getCognome().trim():null);
                    }

                }

            }
            /**
             * Terzo Intermediario  Soggetto Emittente
             */
            if(header.getTerzoIntermediarioOSoggettoEmittente() != null){
                if(header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici() != null){
                    if(header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getIdFiscaleIVA() != null){
                        fAHeader.settIDAIdPaese(header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getIdFiscaleIVA().getIdPaese()!=null?header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getIdFiscaleIVA().getIdPaese().trim():null);
                        fAHeader.settIDAIdCodice(header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getIdFiscaleIVA().getIdCodice()!=null?header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getIdFiscaleIVA().getIdCodice().trim():null);
                    }
                    fAHeader.settIDAIdPaese(header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getCodiceFiscale()!=null?header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getCodiceFiscale().trim():null);
                    if(header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getAnagrafica() != null){
                        fAHeader.settIADenominazione(header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getAnagrafica().getDenominazione()!=null?header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getAnagrafica().getDenominazione().trim():null);
                        fAHeader.settIANome(header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getAnagrafica().getNome()!=null?header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getAnagrafica().getNome().trim():null);
                        fAHeader.settIACognome(header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getAnagrafica().getCognome()!=null?header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getAnagrafica().getCognome().trim():null);
                        fAHeader.settIATitolo(header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getAnagrafica().getTitolo()!=null?header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getAnagrafica().getTitolo().trim():null);
                        fAHeader.settIACodEORI(header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getAnagrafica().getCodEORI()!=null?header.getTerzoIntermediarioOSoggettoEmittente().getDatiAnagrafici().getAnagrafica().getCodEORI().trim():null);

                    }
                }
            }

        }

        if(header.getSoggettoEmittente() != null){
            fAHeader.setSoggettoEmittente(header.getSoggettoEmittente().value());
        }


        List<FatturaElettronicaBodyType> bodies = fe.getFatturaElettronicaBody();
        Set<FABody> faBodies = new HashSet<>();
        for(FatturaElettronicaBodyType body:bodies){
            FABody fab = mapToBody(body);
            fAHeader.addFABody(fab);
        }
        return fAHeader;

    }

    private static FABody mapToBody(FatturaElettronicaBodyType feb){
        FABody faBody = new FABody();

        if(feb != null){
            /**
             *  2.1 Dati Generali
             */
            if(feb.getDatiGenerali() != null){
                /**
                 * 2.1.1 Dati Generali Documento
                 */
                if(feb.getDatiGenerali().getDatiGeneraliDocumento() != null){
                    faBody.setTipoDocumento(feb.getDatiGenerali().getDatiGeneraliDocumento().getTipoDocumento() != null?feb.getDatiGenerali().getDatiGeneraliDocumento().getTipoDocumento().value().trim():null);
                    faBody.setDivisa(feb.getDatiGenerali().getDatiGeneraliDocumento().getDivisa() != null?feb.getDatiGenerali().getDatiGeneraliDocumento().getDivisa().trim():null);
                    faBody.setData(feb.getDatiGenerali().getDatiGeneraliDocumento().getData() != null?feb.getDatiGenerali().getDatiGeneraliDocumento().getData().toString().substring(0,9).trim() :null);
                    faBody.setNumero(feb.getDatiGenerali().getDatiGeneraliDocumento().getNumero() != null?feb.getDatiGenerali().getDatiGeneraliDocumento().getNumero().trim() :null);
                    /**
                     *  2.1.1.5 Dati Generali Documento Dati Ritenuta
                     */
                    if(feb.getDatiGenerali().getDatiGeneraliDocumento().getDatiRitenuta() != null){
                        DatiRitenutaType dr = feb.getDatiGenerali().getDatiGeneraliDocumento().getDatiRitenuta();
                        faBody.setTipoRitenuta(dr != null?dr.getTipoRitenuta().value():null);
                        faBody.setAliquotaRitenuta(dr != null?dr.getAliquotaRitenuta().toString():null);
                        faBody.setCausalePagamento(dr != null?dr.getCausalePagamento().value():null);
                        faBody.setImportoRitenuta(dr != null?dr.getImportoRitenuta().toString():null);

                    }
                    /**
                     *  2.1.1.6 Dati Generali Documento Dati Bollo
                     */
                    if(feb.getDatiGenerali().getDatiGeneraliDocumento().getDatiBollo() != null){
                        //feb.getDatiGenerali().getDatiGeneraliDocumento().datiBollo.
                        DatiBolloType db = feb.getDatiGenerali().getDatiGeneraliDocumento().getDatiBollo();
                        faBody.setImportoBollo(db != null?db.getImportoBollo().toString():null);
                        faBody.setBolloVirtuale(db != null?db.getBolloVirtuale().value():null);
                    }
                    /**
                     *  2.1.1.7 Dati Generali Documento Dati Cassa Previdenziale
                     */
                    if(feb.getDatiGenerali().getDatiGeneraliDocumento().getDatiCassaPrevidenziale() != null){
                        List<DatiCassaPrevidenzialeType> dcps = feb.getDatiGenerali().getDatiGeneraliDocumento().getDatiCassaPrevidenziale();
                        mapToCatiCassaPrevidenziale(faBody, dcps);
                    }
                    /**
                     *   2.1.1.8 Dati Generali Documento Sconto Maggiorazione
                     */
                    if(feb.getDatiGenerali().getDatiGeneraliDocumento().getScontoMaggiorazione() != null){
                        List<ScontoMaggiorazioneType> sms = feb.getDatiGenerali().getDatiGeneraliDocumento().getScontoMaggiorazione();
                        mapToScontoMaggiorazione(faBody,sms);
                    }

                    /**
                     * 2.1.1.9
                     */
                    faBody.setImportoTotaleDocumento(feb.getDatiGenerali().getDatiGeneraliDocumento().getImportoTotaleDocumento()!= null?feb.getDatiGenerali().getDatiGeneraliDocumento().getImportoTotaleDocumento().toString() :null);
                    /**
                     * 2.1.1.10
                     */
                    faBody.setArrotondamento(feb.getDatiGenerali().getDatiGeneraliDocumento().getArrotondamento() != null?feb.getDatiGenerali().getDatiGeneraliDocumento().getArrotondamento().toString() :null);

                    /**
                     * 2.1.1.11 Dati Generali Documento Causale
                     */
                    if(feb.getDatiGenerali().getDatiGeneraliDocumento().getCausale() != null){
                        List<String> cps = feb.getDatiGenerali().getDatiGeneraliDocumento().getCausale();
                        mapToCausales(faBody, cps);
                    }
                    /**
                     * 2.1.1.12 Art73
                     */
                    faBody.setArt73(feb.getDatiGenerali().getDatiGeneraliDocumento().getArt73() != null?feb.getDatiGenerali().getDatiGeneraliDocumento().getArt73().value() :null);
                }
                /**
                 * 2.1.2 Dati Ordine Acquisto
                 */
                if(feb.getDatiGenerali().getDatiOrdineAcquisto() != null){
                    List<DatiDocumentiCorrelatiType> dcs = feb.getDatiGenerali().getDatiOrdineAcquisto();
                    mapToOrdineAcquisto(faBody, dcs);
                }
                /**
                 * 2.1.3 Dati Contratto
                 */
                if(feb.getDatiGenerali().getDatiContratto() != null){
                    List<DatiDocumentiCorrelatiType> dcs = feb.getDatiGenerali().getDatiContratto();
                    mapToContratto(faBody,dcs);
                }
                /**
                 * 2.1.4 Dati Convenzione
                 */
                if(feb.getDatiGenerali().getDatiConvenzione() != null){
                    List<DatiDocumentiCorrelatiType> dcs = feb.getDatiGenerali().getDatiConvenzione();
                    mapToConvenzione(faBody,dcs);
                }
                /**
                 * 2.1.5 Dati Ricezione
                 */
                if(feb.getDatiGenerali().getDatiRicezione() != null){
                    List<DatiDocumentiCorrelatiType> drs = feb.getDatiGenerali().getDatiRicezione();
                    mapToRicezione(faBody, drs);
                }
                /**
                 * 2.1.6 Dati Fatture Collegate
                 */
                if(feb.getDatiGenerali().getDatiFattureCollegate() != null){
                    List<DatiDocumentiCorrelatiType> fats = feb.getDatiGenerali().getDatiFattureCollegate();
                    mapToFattureCollegate(faBody, fats);
                }
                /**
                 * 2.1.7 Dati SAL
                 */
                if(feb.getDatiGenerali().getDatiSAL() != null){
                    List<DatiSALType> sals = feb.getDatiGenerali().getDatiSAL();
                    mapToSAL(faBody, sals);
                }
                /**
                 * 2.1.8 Dati DDT
                 */
                if(feb.getDatiGenerali().getDatiDDT() != null){
                    List<DatiDDTType> ddts = feb.getDatiGenerali().getDatiDDT();
                    mapToDDT(faBody, ddts);
                }
                /**
                 * 2.1.9 Dati Trasporto
                 */
                if(feb.getDatiGenerali().getDatiTrasporto() != null){
                    DatiTrasportoType dts = feb.getDatiGenerali().getDatiTrasporto();
                    if(dts.getDatiAnagraficiVettore() != null){
                        faBody.setIdPaese(dts.getDatiAnagraficiVettore().getIdFiscaleIVA().getIdPaese());
                        faBody.setIdCodice(dts.getDatiAnagraficiVettore().getIdFiscaleIVA().getIdCodice());
                        faBody.setCodiceFiscale(dts.getDatiAnagraficiVettore().getCodiceFiscale());
                        if(dts.getDatiAnagraficiVettore().getAnagrafica() != null){
                            faBody.setDenominazione(dts.getDatiAnagraficiVettore().getAnagrafica().getDenominazione());
                            faBody.setNome(dts.getDatiAnagraficiVettore().getAnagrafica().getNome());
                            faBody.setCognome(dts.getDatiAnagraficiVettore().getAnagrafica().getCognome());
                            faBody.setTitolo(dts.getDatiAnagraficiVettore().getAnagrafica().getTitolo());
                            faBody.setCodEORI(dts.getDatiAnagraficiVettore().getAnagrafica().getCodEORI());
                        }
                        faBody.setNumeroLicenzaGuida(dts.getDatiAnagraficiVettore().getNumeroLicenzaGuida());
                    }
                    faBody.setMezzoTrasporto(dts.getMezzoTrasporto());
                    faBody.setCausaleTrasporto(dts.getCausaleTrasporto());
                    faBody.setNumeroColli((dts.getNumeroColli()!=null? dts.getNumeroColli().toString():null));
                    faBody.setDescrizione(dts.getDescrizione());
                    faBody.setUnitaMisuraPeso(dts.getUnitaMisuraPeso());
                    faBody.setPesoLordo(dts.getPesoLordo()!=null?dts.getPesoLordo().toString():null);
                    faBody.setPesoNetto(dts.getPesoNetto()!=null?dts.getPesoNetto().toString():null);
                    faBody.setDataOraRitiro(dts.getDataOraRitiro()!=null?dts.getDataOraRitiro().toString():null);
                    faBody.setDataInizioTrasporto(dts.getDataInizioTrasporto()!=null?dts.getDataInizioTrasporto().toString():null);
                    faBody.setTipoResa(dts.getTipoResa());
                    if(dts.getIndirizzoResa() != null){
                        faBody.setIndirizzo(dts.getIndirizzoResa().getIndirizzo());
                        faBody.setNumeroCivico(dts.getIndirizzoResa().getNumeroCivico());
                        faBody.setcAP(dts.getIndirizzoResa().getCAP());
                        faBody.setComune(dts.getIndirizzoResa().getComune());
                        faBody.setProvincia(dts.getIndirizzoResa().getProvincia());
                        faBody.setNazione(dts.getIndirizzoResa().getNazione());
                    }
                    faBody.setDataOraConsegna(dts.getDataOraConsegna()!=null?dts.getDataOraConsegna().toString():null);
                }
                /**
                 * 2.1.10 Dati Fattura Principale
                 */
                if(feb.getDatiGenerali().getFatturaPrincipale() != null){
                    FatturaPrincipaleType fp = feb.getDatiGenerali().getFatturaPrincipale();
                    faBody.setNumeroFatturaPrincipale(fp.getNumeroFatturaPrincipale());
                    faBody.setDataFatturaPrincipale(fp.getDataFatturaPrincipale()!=null?fp.getDataFatturaPrincipale().toString():null);

                }

            }
            /**
             *  2.2 Dati Beni Servizi
             */
            if(feb.getDatiBeniServizi() != null){
                DatiBeniServiziType dbs = feb.getDatiBeniServizi();
                /**
                 * 2.2.1 Dettaglio Linee
                 */
                if(dbs.getDettaglioLinee() != null){
                    List<DettaglioLineeType> dls = dbs.getDettaglioLinee();
                    mapToDettaglioLinea(faBody, dls);
                }
                /**
                 * 2.2.2 Dati riepilogo
                 */
                if(dbs.getDatiRiepilogo() != null){
                    List<DatiRiepilogoType> drs = dbs.getDatiRiepilogo();
                    mapToDatiRiepilogo(faBody, drs);
                }
            }
            /**
             * 2.3 Dati Veicoli
             */
            if(feb.getDatiVeicoli() != null){
                DatiVeicoliType dv = feb.getDatiVeicoli();
                faBody.setdVData(dv.getData()!=null?dv.getData().toString():null);
                faBody.setdVTotalePercorso(dv.getTotalePercorso());
            }
            /**
             * 2.4 Dati Pagamamento
             */
            if(feb.getDatiPagamento() != null){
                int qta = feb.getDatiPagamento().size();
                for(int i = 0; i < qta; i++){
                    DatiPagamentoType dpt = feb.getDatiPagamento().get(0);
                    DatiPagamento dp = new DatiPagamento();
                    dp.setCondizioniPagamento(dpt.getCondizioniPagamento().value());
                    mapToDettalioPagamento(dp, dpt.getDettaglioPagamento());
                    faBody.addDatiPagamento(dp);
                }
            }
            /**
             * 2.5 Allegati
             */
            if(feb.getAllegati() != null){
                List<AllegatiType> algs = feb.getAllegati();
                mapToAllegato(faBody, algs);
            }
        }
        return faBody;
    }

    /**
     * Genera set Allegato
     *
     * @param faBody
     * @param algs
     * @return void
     */
    private static void mapToAllegato(FABody faBody, List<AllegatiType> algs) {
        for(AllegatiType alt: algs){
            Allegato al = new Allegato();
            al.setNomeAttachment(alt.getNomeAttachment());
            al.setAlgoritmoCompressione(alt.getAlgoritmoCompressione());
            al.setFormatoAttachment(alt.getFormatoAttachment());
            al.setDescrizioneAttachment(alt.getDescrizioneAttachment());
            faBody.addAllegato(al);
        }
    }

    /**
     * Genera set Dettaglio Pagamento
     *
     * @param dp
     * @param dps
     * @return generated set
     */
    private static void mapToDettalioPagamento(DatiPagamento father, List<DettaglioPagamentoType> dps) {
        for(DettaglioPagamentoType dpt : dps){
            DettaglioPagamento dp = new DettaglioPagamento();
            dp.setBeneficiario(dpt.getBeneficiario());
            dp.setModalitaPagamento(dpt.getModalitaPagamento().value());
            dp.setDataRiferimentoTerminiPagamento(dpt.getDataRiferimentoTerminiPagamento()!=null?dpt.getDataRiferimentoTerminiPagamento().toString():null);
            dp.setGiorniTerminiPagamento(""+dpt.getGiorniTerminiPagamento());
            dp.setDataScadenzaPagamento(dpt.getDataScadenzaPagamento()!=null?dpt.getDataScadenzaPagamento().toString():null);
            dp.setImportoPagamento(dpt.getImportoPagamento()!=null?dpt.getImportoPagamento().toString():null);
            dp.setCodUfficioPostale(dpt.getCodUfficioPostale());
            dp.setCognomeQuietanzante(dpt.getCognomeQuietanzante());
            dp.setNomeQuietanzante(dpt.getNomeQuietanzante());
            dp.setcFQuietanzante(dpt.getCFQuietanzante());
            dp.setTitoloQuietanzante(dpt.getTitoloQuietanzante());
            dp.setIstitutoFinanziario(dpt.getIstitutoFinanziario());
            dp.setiBAN(dpt.getIBAN());
            dp.setaBI(dpt.getABI());
            dp.setcAB(dpt.getCAB());
            dp.setbIC(dpt.getBIC());
            dp.setScontoPagamentoAnticipato(dpt.getScontoPagamentoAnticipato()!=null?dpt.getScontoPagamentoAnticipato().toString():null);
            dp.setDataLimitePagamentoAnticipato(dpt.getDataLimitePagamentoAnticipato()!=null?dpt.getDataLimitePagamentoAnticipato().toString():null);
            dp.setPenalitaPagamentiRitardati(dpt.getPenalitaPagamentiRitardati()!=null?dpt.getPenalitaPagamentiRitardati().toString():null);
            dp.setDataDecorrenzaPenale(dpt.getDataDecorrenzaPenale()!=null?dpt.getDataDecorrenzaPenale().toString():null);
            dp.setCodicePagamento(dpt.getCodicePagamento());
            father.addDettaglioPagamento(dp);
        }
    }

    /**
     * Genera set Dati Riepilogo
     *
     * @param faBody
     * @param drs
     * @return generated set
     */
    private static void mapToDatiRiepilogo(FABody faBody, List<DatiRiepilogoType> drs) {
        for(DatiRiepilogoType drt : drs){
            DatiRiepilogo dr = new DatiRiepilogo();
            dr.setdRAliquotaIVA(drt.getAliquotaIVA()!=null?drt.getAliquotaIVA().toString():null);
            dr.setdRNatura(drt.getNatura()!=null?drt.getNatura().value():null);
            dr.setdRSpeseAccessorie(drt.getSpeseAccessorie()!=null?drt.getSpeseAccessorie().toString():null);
            dr.setdRArrotondamento(drt.getArrotondamento()!=null?drt.getArrotondamento().toString():null);
            dr.setdRImponibileImporto(drt.getImponibileImporto()!=null?drt.getImponibileImporto().toString():null);
            dr.setdRImposta(drt.getImposta()!=null?drt.getImposta().toString():null);
            dr.setdREsigibilitaIVA(drt.getEsigibilitaIVA()!=null?drt.getEsigibilitaIVA().value():null);
            dr.setdRRiferimentoNormativo(drt.getRiferimentoNormativo());
            faBody.addDatiRiepilogo(dr);
        }
    }

    /**
     * Genera set Detaglio linea
     *
     * @param faBody
     * @param dls
     * @return generated set
     */
    private static void mapToDettaglioLinea(FABody faBody, List<DettaglioLineeType> dls) {
        for(DettaglioLineeType dlt : dls){
            DettaglioLinea dl = new DettaglioLinea();
            dl.setNumeroLinea(""+dlt.getNumeroLinea());
            dl.setTipoCessionePrestazione(dlt.getTipoCessionePrestazione()!=null?dlt.getTipoCessionePrestazione().value():null);
            mapToCodiceArticolo(dl, dlt.getCodiceArticolo());
            dl.setdLDescrizione(dlt.getDescrizione());
            dl.setQuantita(dlt.getQuantita()!=null?dlt.getQuantita().toString():null);
            dl.setUnitaMisura(dlt.getUnitaMisura());
            dl.setDataInizioPeriodo(dlt.getDataInizioPeriodo()!=null?dlt.getDataInizioPeriodo().toString():null);
            dl.setDataFinePeriodo(dlt.getDataFinePeriodo()!=null?dlt.getDataFinePeriodo().toString():null);
            dl.setPrezzoUnitario(dlt.getPrezzoUnitario()!=null?dlt.getPrezzoUnitario().toString():null);
            mapToScontoMaggiorazione(dl, dlt.getScontoMaggiorazione());
            dl.setsMPrezzoTotale(dlt.getPrezzoTotale()!=null?dlt.getPrezzoTotale().toString():null);
            dl.setsMAliquotaIVA(dlt.getAliquotaIVA()!=null?dlt.getAliquotaIVA().toString():null);
            dl.setsMRitenuta(dlt.getRitenuta()!=null?dlt.getRitenuta().value():null);
            dl.setsMNatura(dlt.getNatura()!=null?dlt.getNatura().value():null);
            dl.setsMRiferimentoAmministrazione(dlt.getRiferimentoAmministrazione());
            mapToAltriDatiGestionali(dl, dlt.getAltriDatiGestionali());
            faBody.addDettaglioLinea(dl);
        }
    }


    /**
     * Genera set Codice Articolo
     *
     * @param dl
     * @param cas
     * @return generated set
     */
    private static void mapToCodiceArticolo(DettaglioLinea dl, List<CodiceArticoloType> cas){
        for(CodiceArticoloType cat : cas){
            CodiceArticolo ca = new CodiceArticolo();
            ca.setCodiceTipo(cat.getCodiceTipo());
            ca.setCodiceValore(cat.getCodiceValore());
            dl.addCodiceArticolo(ca);
        }
    }

    /**
     * Genera set Altri Dati gestionali
     *
     * @param dl
     * @param dgts
     * @return generated set
     */
    private static void mapToAltriDatiGestionali(DettaglioLinea dl, List<AltriDatiGestionaliType> dgts){
        for(AltriDatiGestionaliType dgt : dgts){
            AltriDatiGestionali dg = new AltriDatiGestionali();
            dg.setRiferimentoData(dgt.getRiferimentoData()!=null?dgt.getRiferimentoData().toString():null);
            dg.setRiferimentoNumero(dgt.getRiferimentoNumero()!=null?dgt.getRiferimentoNumero().toString():null);
            dg.setRiferimentoTesto(dgt.getRiferimentoTesto());
            dg.setTipoDato(dgt.getTipoDato());
            dl.addAltriDatiGestionali(dg);
        }
    }


    /**
     * Genera set DDT
     *
     * @param faBody
     * @param ddts
     * @return generated set
     */
    private static void mapToDDT(FABody faBody, List<DatiDDTType> ddts) {
        for(DatiDDTType ddt : ddts){
            DDT o = new DDT();
            o.setDataDDT(ddt.getDataDDT()!=null?ddt.getDataDDT().toString():null);
            o.setNumeroDDT(ddt.getNumeroDDT());
            mapToNumeroLineas(o, ddt.getRiferimentoNumeroLinea());
            faBody.addDDT(o);
        }
    }

    /**
     * Genera set SAL
     *
     * @param faBody
     * @param sals
     * @return converted set
     */
    private static void  mapToSAL(FABody faBody, List<DatiSALType> sals) {
        for(DatiSALType sal : sals){
            SAL fc = new SAL();
            fc.setRiferimentoFase(""+sal.getRiferimentoFase());
            faBody.addSAL(fc);
        }
    }

    /**
     * Genera set FattureCollegate
     *
     * @param faBody
     * @param fats
     * @return converted set
     */
    private static void mapToFattureCollegate(FABody faBody, List<DatiDocumentiCorrelatiType> fats) {
        for(DatiDocumentiCorrelatiType fat : fats){
            FatturaCollegata fc = new FatturaCollegata();
            fc.setCodiceCIG(fat.getCodiceCIG());
            fc.setCodiceCommessaConvenzione(fat.getCodiceCommessaConvenzione());
            fc.setCodiceCUP(fat.getCodiceCUP());
            fc.setdOAData(fat.getData()!=null?fat.getData().toString():null);
            fc.setNumItem(fat.getNumItem());
            faBody.addFatturaCollegata(fc);
        }
    }

    /**
     * Genera set Ricezione
     *
     * @param faBody
     * @param drs
     * @return converted set
     */
    private static void mapToRicezione(FABody faBody, List<DatiDocumentiCorrelatiType> drs) {
        for(DatiDocumentiCorrelatiType dc : drs){
            Ricezione r = new Ricezione();
            r.setCodiceCIG(dc.getCodiceCIG());
            r.setCodiceCommessaConvenzione(dc.getCodiceCommessaConvenzione());
            r.setCodiceCUP(dc.getCodiceCUP());
            r.setdOAData(dc.getData()!=null?dc.getData().toString():null);
            r.setNumItem(dc.getNumItem());
            faBody.addRicezione(r);
        }
    }

    /**
     * Genera set Convenzione
     * @param dcs
     * @return converted set
     */
    private static void mapToConvenzione(FABody faBody, List<DatiDocumentiCorrelatiType> dcs) {
        for(DatiDocumentiCorrelatiType dc : dcs){
            Convenzione c = new Convenzione();
            c.setCodiceCIG(dc.getCodiceCIG());
            c.setCodiceCommessaConvenzione(dc.getCodiceCommessaConvenzione());
            c.setCodiceCUP(dc.getCodiceCUP());
            c.setdOAData(dc.getData()!=null?dc.getData().toString():null);
            c.setNumItem(dc.getNumItem());
            faBody.addConvenzione(c);
        }
    }

    /**
     * Genera set Contratto
     *
     * @param faBody
     * @param dcs
     * @return converted set
     */
    private static void mapToContratto(FABody faBody, List<DatiDocumentiCorrelatiType> dcs) {
        for(DatiDocumentiCorrelatiType dc : dcs){
            Contratto c = new Contratto();
            c.setCodiceCIG(dc.getCodiceCIG());
            c.setCodiceCommessaConvenzione(dc.getCodiceCommessaConvenzione());
            c.setCodiceCUP(dc.getCodiceCUP());
            c.setdOAData(dc.getData()!=null?dc.getData().toString():null);
            c.setNumItem(dc.getNumItem());
            faBody.addContratto(c);
        }
    }

    /**
     * Generates OrdineAcquisto set
     *
     * @param faBody
     * @param dcs
     * @return converted set
     */
    private static void mapToOrdineAcquisto(FABody faBody, List<DatiDocumentiCorrelatiType> dcs) {
        for(DatiDocumentiCorrelatiType dc : dcs){
            OrdineAcquisto oa = new OrdineAcquisto();
            oa.setCodiceCIG(dc.getCodiceCIG());
            oa.setCodiceCommessaConvenzione(dc.getCodiceCommessaConvenzione());
            oa.setCodiceCUP(dc.getCodiceCUP());
            oa.setdOAData(dc.getData()!=null?dc.getData().toString():null);
            oa.setNumItem(dc.getNumItem());
            mapToNumeroLineas(oa, dc.getRiferimentoNumeroLinea());
            faBody.addOrdineAcquisto(oa);
        }
    }

    private static void mapToNumeroLineas(OrdineAcquisto oa, List<Integer> riferimentoNumeroLinea) {
        for(Integer rnl : riferimentoNumeroLinea){
            NumeroLinea nl = new NumeroLinea();
            nl.setRiferimentoNumeroLinea(rnl.toString());
            oa.addNumeroLinea(nl);
        }
    }

    private static void mapToNumeroLineas(DDT o, List<Integer> riferimentoNumeroLinea) {
        for(Integer rnl : riferimentoNumeroLinea){
            NumeroLinea nl = new NumeroLinea();
            nl.setRiferimentoNumeroLinea(rnl.toString());
            o.addNumeroLinea(nl);
        }
    }

    /**
     * Generates Causales set
     *
     * @param faBody
     * @param cps
     * @return converted set
     */
    private static void mapToCausales(FABody faBody, List<String> cps) {
        for(String causa : cps){
            Causale c = new Causale();
            c.setCausale(causa);
            faBody.addCausale(c);
        }
    }

    /**
     * Generates the CassaPrevidenziale set
     *
     * @param faBody
     * @param sms
     * @return converted set
     */
    private static void mapToScontoMaggiorazione(FABody faBody, List<ScontoMaggiorazioneType> sms) {
        for(ScontoMaggiorazioneType sm : sms){
            ScontoMaggiorazione scm = new ScontoMaggiorazione();
            scm.setsMImporto(sm.getImporto()!=null?sm.getImporto().toString():null);
            scm.setsMPercentuale(sm.getPercentuale()!=null?sm.getPercentuale().toString():null);
            scm.setsMTipo(sm.getTipo()!=null?sm.getTipo().value():null);
            faBody.addScontoMaggiorazione(scm);
        }
    }

    /**
     * Generates the CassaPrevidenziale set
     *
     * @param faBody
     * @param sms
     * @return converted set
     */
    private static void mapToScontoMaggiorazione(DettaglioLinea dl, List<ScontoMaggiorazioneType> sms) {
        for(ScontoMaggiorazioneType sm : sms){
            ScontoMaggiorazione scm = new ScontoMaggiorazione();
            scm.setsMImporto(sm.getImporto()!=null?sm.getImporto().toString():null);
            scm.setsMPercentuale(sm.getPercentuale()!=null?sm.getPercentuale().toString():null);
            scm.setsMTipo(sm.getTipo()!=null?sm.getTipo().value():null);
            dl.addScontoMaggiorazione(scm);
        }
    }

    /**
     * Generates the CassaPrevidenziale Set
     *
     *
     * @param faBody
     * @param dcps
     * @return converted set
     */
    private static void mapToCatiCassaPrevidenziale(FABody faBody, List<DatiCassaPrevidenzialeType> dcps) {
        for(DatiCassaPrevidenzialeType dcp : dcps ) {
            CassaPrevidenziale cp = new CassaPrevidenziale();
            cp.setAlCassa(dcp.getAlCassa()!=null?dcp.getAlCassa().toString():null);
            cp.setAliquotaIVA(dcp.getAliquotaIVA()!=null?dcp.getAliquotaIVA().toString():null);
            cp.setImponibileCassa(dcp.getImponibileCassa()!=null?dcp.getImponibileCassa().toString():null);
            cp.setImportoContributoCassa(dcp.getImportoContributoCassa()!=null?dcp.getImportoContributoCassa().toString():null);
            cp.setNatura(dcp.getNatura()!=null?dcp.getNatura().value():null);
            cp.setRiferimentoAmministrazione(dcp.getRiferimentoAmministrazione());
            cp.setRitenuta(dcp.getRitenuta()!=null?dcp.getRitenuta().value():null);
            cp.setTipoCassa(dcp.getTipoCassa()!=null?dcp.getTipoCassa().value():null);
            faBody.addCassaPrevidenziale(cp);
        }
    }

}
