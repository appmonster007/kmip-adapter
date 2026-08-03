package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.NonceId;

public class NonceIdBenchmarkSubject extends KmipBenchmarkSubject<NonceId> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public NonceIdBenchmarkSubject() throws Exception {
    NonceId subject = NonceId.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(subject, NonceId.class);
  }

  @Override
  public String name() {
    return "NonceId";
  }

}