package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.DigestedData;

public class DigestedDataBenchmarkSubject extends KmipBenchmarkSubject<DigestedData> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public DigestedDataBenchmarkSubject() throws Exception {
    DigestedData subject = DigestedData.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(subject, DigestedData.class);
  }

  @Override
  public String name() {
    return "DigestedData";
  }
}