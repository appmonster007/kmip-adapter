package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.EncryptOpResponsePayload;

/**
 * Benchmark subject for {@link EncryptOpResponsePayload}.
 */
public class EncryptOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<EncryptOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link EncryptOpResponsePayloadBenchmarkSubject}.
   */
  public EncryptOpResponsePayloadBenchmarkSubject() throws Exception {
    EncryptOpResponsePayload subject = EncryptOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
    initialize(subject, EncryptOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "EncryptOpResponsePayload";
  }
}
