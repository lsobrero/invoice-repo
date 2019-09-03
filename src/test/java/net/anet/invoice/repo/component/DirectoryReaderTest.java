package net.anet.invoice.repo.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.io.FileUtils.listFiles;
import static org.assertj.core.api.Assertions.assertThat;

public class DirectoryReaderTest {

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testStartUpProdServletContext(){
            Collection<File> listOfFiles = listFiles(new File("src/test/resources/invoices/ext/"), new String[]{"xml","XML"}, true);
            assertThat(listOfFiles.size()).isEqualTo(2);
    }
}
