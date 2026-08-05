package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ClientCorrelationValue;

/**
 * Benchmark subject for {@link ClientCorrelationValue}.
 */
public class ClientCorrelationValueBenchmarkSubject
    extends KmipBenchmarkSubject<ClientCorrelationValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ClientCorrelationValueBenchmarkSubject}.
   */
  public ClientCorrelationValueBenchmarkSubject() throws Exception {
    ClientCorrelationValue subject = ClientCorrelationValue.of("default-string");
    initialize(subject, ClientCorrelationValue.class);
  }

  @Override
  public String name() {
    return "ClientCorrelationValue";
  }
}