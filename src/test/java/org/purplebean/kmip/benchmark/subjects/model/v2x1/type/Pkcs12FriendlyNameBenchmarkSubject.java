package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.Pkcs12FriendlyName;

/**
 * Benchmark subject for {@link Pkcs12FriendlyName}.
 */
public class Pkcs12FriendlyNameBenchmarkSubject extends KmipBenchmarkSubject<Pkcs12FriendlyName> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link Pkcs12FriendlyNameBenchmarkSubject}.
   */
  public Pkcs12FriendlyNameBenchmarkSubject() throws Exception {
    Pkcs12FriendlyName subject = Pkcs12FriendlyName.of("default-string");
    initialize(subject, Pkcs12FriendlyName.class);
  }

  @Override
  public String name() {
    return "Pkcs12FriendlyName";
  }
}