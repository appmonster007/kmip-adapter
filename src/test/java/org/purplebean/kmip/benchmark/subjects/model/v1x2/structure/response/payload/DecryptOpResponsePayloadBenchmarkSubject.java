package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.DecryptOpResponsePayload;

/**
 * Benchmark subject for {@link DecryptOpResponsePayload}.
 */
public class DecryptOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DecryptOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link DecryptOpResponsePayloadBenchmarkSubject}.
   */
  public DecryptOpResponsePayloadBenchmarkSubject() throws Exception {
    DecryptOpResponsePayload subject = DecryptOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
    initialize(subject, DecryptOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "DecryptOpResponsePayload";
  }
}
