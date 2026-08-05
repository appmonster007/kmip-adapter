package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SymmetricKey;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.GetOpResponsePayload;

/**
 * Benchmark subject for {@link GetOpResponsePayload}.
 */
public class GetOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<GetOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link GetOpResponsePayloadBenchmarkSubject}.
   */
  public GetOpResponsePayloadBenchmarkSubject() throws Exception {
    GetOpResponsePayload subject = GetOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .object(SymmetricKey
            .builder()
            .keyBlock(KeyBlock
                .builder()
                .keyFormatType(KeyFormatType.Standard.RAW.inst())
                .build())
            .build())
        .build();
    initialize(subject, GetOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "GetOpResponsePayload";
  }
}
