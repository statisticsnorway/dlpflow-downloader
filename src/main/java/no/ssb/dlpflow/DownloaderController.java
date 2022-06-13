package no.ssb.dlpflow;


import com.google.common.annotations.VisibleForTesting;
import io.micronaut.http.*;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.ssb.dlpflow.util.PrincipalUtil;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.io.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@Secured(SecurityRule.IS_AUTHENTICATED)
public class DownloaderController {
    private final DownloadService downloadService;
    //private final String url_prefix = "https://europe-north1-maven.pkg.dev/artifact-registry-14da/maven-snapshots/no/ssb/dlpflow/auto-data-tokenize/";
    @Get(value="/dlpflow/download/{url}", consumes = {MediaType.MULTIPART_FORM_DATA})
    public HttpResponse<byte[]> downloadJar(Principal principal, String url) throws IOException {
        log.info("AUDIT {}", PrincipalUtil.auditInfoOf(principal));
        log.info("get: {}", url);
        String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
        if (Files.notExists(Paths.get(fileName))) {
            downloadService.download(url);
        }

        return HttpResponse.ok(Files.readAllBytes(new File(fileName).toPath()))
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\""+fileName+"\"");
    }

    @Get(value="/dlpflow/test", consumes = {MediaType.MULTIPART_FORM_DATA})
    public HttpResponse<byte[]> downloadTest(Principal principal) throws IOException {
        log.info("AUDIT {}", PrincipalUtil.auditInfoOf(principal));
        String url = "https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.36.0.3/sqlite-jdbc-3.36.0.3.jar";
        return this.downloadJar(principal, url);
    }

}
