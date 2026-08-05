package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateUri;

/**
 * Benchmark subject for {@link ValidationCertificateUri}.
 */
public class ValidationCertificateUriBenchmarkSubject
    extends KmipBenchmarkSubject<ValidationCertificateUri> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ValidationCertificateUriBenchmarkSubject}.
   */
  public ValidationCertificateUriBenchmarkSubject() throws Exception {
    ValidationCertificateUri subject = ValidationCertificateUri.of("default-string");
    initialize(subject, ValidationCertificateUri.class);
  }

  @Override
  public String name() {
    return "ValidationCertificateUri";
  }
}