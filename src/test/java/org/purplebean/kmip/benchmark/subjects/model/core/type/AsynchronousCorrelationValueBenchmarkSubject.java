package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;

/**
 * Benchmark subject for {@link AsynchronousCorrelationValue}.
 */
public class AsynchronousCorrelationValueBenchmarkSubject
    extends KmipBenchmarkSubject<AsynchronousCorrelationValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link AsynchronousCorrelationValueBenchmarkSubject}.
   */
  public AsynchronousCorrelationValueBenchmarkSubject() throws Exception {
    AsynchronousCorrelationValue asynchronousCorrelationValue =
        AsynchronousCorrelationValue.of(new byte[] {0x01, 0x02, 0x03});
    initialize(asynchronousCorrelationValue, AsynchronousCorrelationValue.class);
  }

  @Override
  public String name() {
    return "AsynchronousCorrelationValue";
  }

}