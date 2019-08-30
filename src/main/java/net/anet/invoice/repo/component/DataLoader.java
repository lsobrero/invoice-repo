package net.anet.invoice.repo.component;

import it.olivetti.hdo.dispatcher.models.pa.FatturaElettronicaType;
import net.anet.invoice.repo.domain.FABody;
import net.anet.invoice.repo.domain.FAHeader;
import net.anet.invoice.repo.fattel.FattelMapper;
import net.anet.invoice.repo.fattel.XMLParser;
import net.anet.invoice.repo.repository.FABodyRepository;
import net.anet.invoice.repo.repository.FAHeaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collection;

import static org.apache.commons.io.FileUtils.listFiles;

@Component
public class DataLoader implements ApplicationRunner {

    private final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private FAHeaderRepository faHeaderRepository;
    @Autowired
    private FABodyRepository faBodyRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int numFiles=0;
        log.debug("Starting DataLoader::process");
//        if(true)
//            return;
//        Collection<File> listOfFiles = listFiles(new File("/home/luis/lavoro/fatturaPoli/invoice/src/test/resources/files"), new String[]{"xml"}, true);
        Collection<File> listOfFiles = listFiles(new File("/home/luis/lavoro/fatturaPoli/invoices/files/"), new String[]{"xml"}, true);
        for(File file: listOfFiles) {
            try {
//                FatturaElettronicaType ft = (FatturaElettronicaType) XMLParser.parseXML("/home/luis/lavoro/fatturaPoli/invoice/src/test/resources/files/fatt_0001.xml");
                if(file.isFile()){
                    log.debug("DataLoader::process file=",file.toString());
                    FatturaElettronicaType ft = (FatturaElettronicaType) XMLParser.parseXML(file.toString());
                    FAHeader fep = FattelMapper.mapToHeader(ft);
                    faHeaderRepository.save(fep);
                    numFiles++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(numFiles > 10000)
                break;
        }
    }
}
