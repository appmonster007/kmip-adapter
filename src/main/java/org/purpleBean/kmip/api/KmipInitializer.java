package org.purpleBean.kmip.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

public class KmipInitializer {

    private static final AtomicBoolean initialized = new AtomicBoolean(false);

    /**
     * Initializes all KMIP data types listed in the service definition file.
     * <p>
     * This method ensures that the static initializers of all discovered POJOs are executed.
     * It is idempotent and thread-safe; the initialization process will only run once.
     */
    public static void initialize() {
        if (initialized.compareAndSet(false, true)) {
            try {
                ClassLoader classLoader = KmipInitializer.class.getClassLoader();
                InputStream inputStream = classLoader.getResourceAsStream("META-INF/services/org.purpleBean.kmip.api.KmipDataType");
                if (inputStream != null) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                        reader.lines().forEach(className -> {
                            try {
                                Class.forName(className);
                            } catch (ClassNotFoundException e) {
                                System.err.println("[KmipInitializer] Failed to load KMIP data type "
                                    + className + ": " + e.getMessage());
                            }
                        });
                    }
                }
            } catch (Exception e) {
                // Wrap in a runtime exception to signal a critical failure in the initialization process.
                throw new RuntimeException("Failed to initialize KMIP classes", e);
            }
        }
    }
}
