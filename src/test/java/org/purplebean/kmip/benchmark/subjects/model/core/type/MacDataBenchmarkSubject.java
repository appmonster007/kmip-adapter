package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.MacData;

/**
 * Benchmark subject for {@link MacData}.
 */
public class MacDataBenchmarkSubject extends KmipBenchmarkSubject<MacData> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link MacDataBenchmarkSubject}.
   */
  public MacDataBenchmarkSubject() throws Exception {
    byte[] data = "test mac data".getBytes();
    MacData macData = MacData.of(ByteBuffer.wrap(data));
    initialize(macData, MacData.class);
  }

  @Override
  public String name() {
    return "MACData";
  }

}
