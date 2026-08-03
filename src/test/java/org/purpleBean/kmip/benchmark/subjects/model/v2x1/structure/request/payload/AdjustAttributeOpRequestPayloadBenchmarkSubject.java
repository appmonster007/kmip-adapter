package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.enumeration.AdjustmentType;
import org.purplebean.kmip.model.v2x1.structure.CurrentAttribute;
import org.purplebean.kmip.model.v2x1.structure.request.payload.AdjustAttributeOpRequestPayload;

public class AdjustAttributeOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<AdjustAttributeOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public AdjustAttributeOpRequestPayloadBenchmarkSubject() throws Exception {
    AdjustAttributeOpRequestPayload subject = AdjustAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("adj-attr-uid-1")
            .build())
        .currentAttribute(CurrentAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .adjustmentType(AdjustmentType.Standard.INCREMENT.inst())
        .build();
    initialize(subject, AdjustAttributeOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "AdjustAttributeOpRequestPayload";
  }
}
