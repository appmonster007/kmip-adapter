package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.ValidationCertificateIdentifier;

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