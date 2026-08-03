package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;

public class AttestationMeasurementBenchmarkSubject
    extends KmipBenchmarkSubject<AttestationMeasurement> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public AttestationMeasurementBenchmarkSubject() throws Exception {
    AttestationMeasurement subject =
        AttestationMeasurement.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(subject, AttestationMeasurement.class);
  }

  @Override
  public String name() {
    return "AttestationMeasurement";
  }

}