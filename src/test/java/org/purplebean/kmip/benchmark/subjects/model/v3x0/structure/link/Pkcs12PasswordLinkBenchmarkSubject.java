package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.Pkcs12PasswordLink;

/**
 * Benchmark subject for {@link Pkcs12PasswordLink}.
 */
public class Pkcs12PasswordLinkBenchmarkSubject extends KmipBenchmarkSubject<Pkcs12PasswordLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link Pkcs12PasswordLinkBenchmarkSubject}.
   */
  public Pkcs12PasswordLinkBenchmarkSubject() throws Exception {
    Pkcs12PasswordLink subject = Pkcs12PasswordLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, Pkcs12PasswordLink.class);
  }

  @Override
  public String name() {
    return "Pkcs12PasswordLink";
  }
}