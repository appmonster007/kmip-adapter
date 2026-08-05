package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.response.payload.HashOpResponsePayload;

/**
 * Benchmark subject for {@link HashOpResponsePayload}.
 */
public class HashOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<HashOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link HashOpResponsePayloadBenchmarkSubject}.
   */
  public HashOpResponsePayloadBenchmarkSubject() throws Exception {
    HashOpResponsePayload subject = HashOpResponsePayload
        .builder()
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
    initialize(subject, HashOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "HashOpResponsePayload";
  }
}
