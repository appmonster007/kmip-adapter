package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.ClientCorrelationValue;

public class ClientCorrelationValueBenchmarkSubject
    extends KmipBenchmarkSubject<ClientCorrelationValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ClientCorrelationValueBenchmarkSubject() throws Exception {
    ClientCorrelationValue subject = ClientCorrelationValue.of("default-string");
    initialize(subject, ClientCorrelationValue.class);
  }

  @Override
  public String name() {
    return "ClientCorrelationValue";
  }
}