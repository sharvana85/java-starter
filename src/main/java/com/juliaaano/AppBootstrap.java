package com.juliaaano;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

public class AppBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(AppBootstrap.class);

    public static void main(String... args) {

        MDC.put("Correlation-Id", "correlation-id");
        printApplicationAsciiBanner("application-ascii-banner.txt");
    }

    private static void printApplicationAsciiBanner(final String fileName) {

        try {

            final InputStream inputStream = getResourceAsStream(fileName)
                    .orElseThrow(() -> new IllegalArgumentException("File " + fileName + " not in the classpath."));

            final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

        } catch (final Exception ex) {

            logger.warn("Could not display application ascii banner.", ex);
        }
    }

    private static Optional<InputStream> getResourceAsStream(final String fileName) {

        return Optional.ofNullable(
                AppBootstrap.class.getClassLoader().getResourceAsStream(fileName)
        );
    }
}
