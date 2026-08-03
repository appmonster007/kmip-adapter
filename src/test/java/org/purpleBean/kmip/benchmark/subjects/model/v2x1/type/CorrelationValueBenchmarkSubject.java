package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.CorrelationValue;

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