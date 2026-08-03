package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.NewAttribute;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.SetAttributeOpRequestPayload;

public class SetAttributeOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetAttributeOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public SetAttributeOpRequestPayloadBenchmarkSubject() throws Exception {
    SetAttributeOpRequestPayload subject = SetAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("set-attr-uid-1")
            .build())
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
    initialize(subject, SetAttributeOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "SetAttributeOpRequestPayload";
  }
}
