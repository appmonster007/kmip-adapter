package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.CorrelationValue;

public class CorrelationValueBenchmarkSubject extends KmipBenchmarkSubject<CorrelationValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CorrelationValueBenchmarkSubject() throws Exception {
    CorrelationValue subject = CorrelationValue.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(subject, CorrelationValue.class);
  }

  @Override
  public String name() {
    return "CorrelationValue";
  }
}