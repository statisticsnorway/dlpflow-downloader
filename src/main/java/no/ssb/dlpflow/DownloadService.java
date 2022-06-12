package no.ssb.dlpflow;

import jakarta.inject.Singleton;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Singleton
@RequiredArgsConstructor
public class DownloadService {
    public void download(String url) {
        //String url = "https://europe-north1-maven.pkg.dev/artifact-registry-14da/maven-snapshots/no/ssb/dlpflow/auto-data-tokenize/"+version+"/auto-data-tokenize-"+version+".jar";
        String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }

    }
}
