package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.ShortUniqueIdentifier;

public class ShortUniqueIdentifierBenchmarkSubject
    extends KmipBenchmarkSubject<ShortUniqueIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ShortUniqueIdentifierBenchmarkSubject() throws Exception {
    ShortUniqueIdentifier subject =
        ShortUniqueIdentifier.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(subject, ShortUniqueIdentifier.class);
  }

  @Override
  public String name() {
    return "ShortUniqueIdentifier";
  }
}