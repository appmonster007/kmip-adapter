package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.nio.ByteBuffer;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.PSource;

public class PSourceBenchmarkSubject extends KmipBenchmarkSubject<PSource> {

  public PSourceBenchmarkSubject() throws Exception {
    PSource pSource = PSource.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(pSource, PSource.class);
  }

  @Override
  public String name() {
    return "PSource";
  }

}
