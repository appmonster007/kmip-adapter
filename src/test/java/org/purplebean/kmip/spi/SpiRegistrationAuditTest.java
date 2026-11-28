package org.purplebean.kmip.spi;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purplebean.kmip.codec.json.serializer.api.KmipDataTypeJsonSerializer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.serializer.api.KmipDataTypeTtlvSerializer;
import org.purplebean.kmip.codec.xml.deserializer.api.KmipDataTypeXmlDeserializer;
import org.purplebean.kmip.codec.xml.serializer.api.KmipDataTypeXmlSerializer;

/**
 * Audits the {@code META-INF/services} files of the main sources: every concrete implementation of
 * a service interface must be listed, and every listed line must resolve to a class that
 * implements that interface. All gaps are reported in one failure message.
 */
class SpiRegistrationAuditTest {

  // Only the interfaces listed here are audited: add new service interfaces to this list.
  private static final List<Class<?>> SERVICES =
      List.of(
          KmipDataType.class,
          KmipDataTypeJsonSerializer.class,
          KmipDataTypeJsonDeserializer.class,
          KmipDataTypeXmlSerializer.class,
          KmipDataTypeXmlDeserializer.class,
          KmipDataTypeTtlvSerializer.class,
          KmipDataTypeTtlvDeserializer.class);

  @Test
  void everyConcreteImplementationIsRegisteredAndEveryEntryResolves() throws Exception {
    Path classesRoot =
        Path.of(KmipDataType.class.getProtectionDomain().getCodeSource().getLocation().toURI());
    List<Class<?>> mainClasses = loadMainClasses(classesRoot);
    List<String> gaps = new ArrayList<>();

    for (Class<?> service : SERVICES) {
      Path file = classesRoot.resolve("META-INF/services/" + service.getName());
      Set<String> listed = readEntries(file);
      Set<String> concrete =
          mainClasses.stream()
              .filter(c -> service.isAssignableFrom(c) && c != service)
              .filter(c -> !c.isInterface() && !Modifier.isAbstract(c.getModifiers()))
              // Member, anonymous and local classes are excluded on purpose: the audit targets
              // top-level service providers.
              .filter(c -> !c.isAnonymousClass() && !c.isMemberClass() && !c.isLocalClass())
              .map(Class::getName)
              .collect(Collectors.toCollection(TreeSet::new));

      for (String name : concrete) {
        if (!listed.contains(name)) {
          gaps.add(service.getSimpleName() + ": not registered: " + name);
        }
      }
      for (String name : listed) {
        try {
          Class<?> c = Class.forName(name, false, getClass().getClassLoader());
          if (!service.isAssignableFrom(c)) {
            gaps.add(service.getSimpleName() + ": listed but not an implementation: " + name);
          }
        } catch (ClassNotFoundException e) {
          gaps.add(service.getSimpleName() + ": listed but class not found: " + name);
        }
      }
    }

    assertThat(gaps).as("SPI registration gaps").isEmpty();
  }

  private static Set<String> readEntries(Path file) throws IOException {
    assertThat(file).as("service file").exists();
    return Files.readAllLines(file).stream()
        .map(String::trim)
        .filter(line -> !line.isEmpty() && !line.startsWith("#"))
        .collect(Collectors.toCollection(TreeSet::new));
  }

  private static List<Class<?>> loadMainClasses(Path root) throws URISyntaxException {
    try (Stream<Path> files = Files.walk(root)) {
      return files
          .filter(p -> p.toString().endsWith(".class"))
          .map(p -> root.relativize(p).toString())
          .filter(n -> !n.contains("module-info") && !n.contains("package-info"))
          .map(n -> n.substring(0, n.length() - ".class".length()).replace(File.separatorChar, '.'))
          .map(SpiRegistrationAuditTest::load)
          .collect(Collectors.toList());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private static Class<?> load(String name) {
    try {
      return Class.forName(name, false, SpiRegistrationAuditTest.class.getClassLoader());
    } catch (ClassNotFoundException | LinkageError e) {
      throw new IllegalStateException("Cannot load " + name, e);
    }
  }
}
