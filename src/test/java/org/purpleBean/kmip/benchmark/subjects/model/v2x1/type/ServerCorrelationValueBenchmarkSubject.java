package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.ServerCorrelationValue;

public class ServerCorrelationValueBenchmarkSubject
    extends KmipBenchmarkSubject<ServerCorrelationValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ServerCorrelationValueBenchmarkSubject() throws Exception {
    ServerCorrelationValue subject = ServerCorrelationValue.of("default-string");
    initialize(subject, ServerCorrelationValue.class);
  }

  @Override
  public String name() {
    return "ServerCorrelationValue";
  }
}