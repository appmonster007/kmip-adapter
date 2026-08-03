package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateIdentifier;

public class ValidationCertificateIdentifierBenchmarkSubject
    extends KmipBenchmarkSubject<ValidationCertificateIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ValidationCertificateIdentifierBenchmarkSubject() throws Exception {
    ValidationCertificateIdentifier subject = ValidationCertificateIdentifier.of("default-string");
    initialize(subject, ValidationCertificateIdentifier.class);
  }

  @Override
  public String name() {
    return "ValidationCertificateIdentifier";
  }
}