package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.enumeration.AdjustmentType;
import org.purpleBean.kmip.model.v2x1.structure.CurrentAttribute;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.AdjustAttributeOpRequestPayload;

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
