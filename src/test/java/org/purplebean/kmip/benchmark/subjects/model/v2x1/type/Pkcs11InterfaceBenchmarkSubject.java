package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.Pkcs11Interface;

/**
 * Benchmark subject for {@link Pkcs11Interface}.
 */
public class Pkcs11InterfaceBenchmarkSubject extends KmipBenchmarkSubject<Pkcs11Interface> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link Pkcs11InterfaceBenchmarkSubject}.
   */
  public Pkcs11InterfaceBenchmarkSubject() throws Exception {
    Pkcs11Interface subject = Pkcs11Interface.of("default-string");
    initialize(subject, Pkcs11Interface.class);
  }

  @Override
  public String name() {
    return "Pkcs11Interface";
  }
}