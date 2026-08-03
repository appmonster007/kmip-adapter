package org.purplebean.kmip.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Handles the dynamic initialization of KMIP (Key Management Interoperability Protocol) data types.
 * <p>
 * This class uses the Java Service Provider Interface (SPI) pattern to discover and load all
 * classes that implement the {@link KmipDataType} interface. It reads a service definition file
 * located at {@code META-INF/services/org.purplebean.kmip.api.KmipDataType}, which should contain
 * the fully qualified names of all concrete KMIP data type implementations.
 *
 * <p><b>Key Responsibilities:</b></p>
 * <ul>
 *   <li><b>Class Initialization:</b> For each class name found, it uses {@code Class.forName()}
 *       to ensure the class is loaded and its static initializers are executed. This is crucial
 *       because KMIP data types register themselves with central registries (e.g.,
 *       {@link KmipDataType#TAG_REGISTRY}) in their static blocks.</li>
 *   <li><b>Idempotency:</b> The initialization process is designed to run only once, even if
 *       {@link #initialize()} is called multiple times, thanks to an {@link AtomicBoolean} flag.
 *       This makes it safe to call from multiple places without side effects.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * The {@link #initialize()} method should be called at the application's startup to ensure that
 * all KMIP data types are registered before any KMIP messages are processed.
 *
 * <pre>
 * {@code
 * public class MyApplication {
 *     public static void main(String[] args) {
 *         KmipInitializer.initialize();
 *         // Proceed with application logic...
 *     }
 * }
 * }
 * </pre>
 *
 * @see KmipDataType
 * @see java.util.ServiceLoader
 */
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
        InputStream inputStream = classLoader.getResourceAsStream(
            "META-INF/services/org.purplebean.kmip.api.KmipDataType");
        if (inputStream != null) {
          try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            reader
                .lines()
                .forEach(className -> {
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
