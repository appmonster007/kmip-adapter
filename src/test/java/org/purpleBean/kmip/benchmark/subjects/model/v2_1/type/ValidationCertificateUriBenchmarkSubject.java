package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.ValidationCertificateUri;

public class ValidationCertificateUriBenchmarkSubject
    extends KmipBenchmarkSubject<ValidationCertificateUri> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ValidationCertificateUriBenchmarkSubject() throws Exception {
    ValidationCertificateUri subject = ValidationCertificateUri.of("default-string");
    initialize(subject, ValidationCertificateUri.class);
  }

  @Override
  public String name() {
    return "ValidationCertificateUri";
  }
}