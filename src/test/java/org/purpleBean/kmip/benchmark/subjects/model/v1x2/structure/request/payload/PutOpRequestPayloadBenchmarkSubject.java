package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SymmetricKey;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.PutOpRequestPayload;

public class PutOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<PutOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public PutOpRequestPayloadBenchmarkSubject() throws Exception {
    PutOpRequestPayload subject = PutOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .putFunction(PutFunction.of(PutFunction.Standard.NEW))
        .object(SymmetricKey
            .builder()
            .keyBlock(KeyBlock
                .builder()
                .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                .build())
            .build())
        .build();
    initialize(subject, PutOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "PutOpRequestPayload";
  }
}
