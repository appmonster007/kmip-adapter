package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.NewAttribute;
import org.purplebean.kmip.model.v2x1.structure.response.payload.AdjustAttributeOpResponsePayload;

public class AdjustAttributeOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<AdjustAttributeOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public AdjustAttributeOpResponsePayloadBenchmarkSubject() throws Exception {
    AdjustAttributeOpResponsePayload subject = AdjustAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("adj-attr-resp-uid-1")
            .build())
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
    initialize(subject, AdjustAttributeOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "AdjustAttributeOpResponsePayload";
  }
}
