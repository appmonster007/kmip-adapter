package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.NewAttribute;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.AddAttributeOpRequestPayload;

public class AddAttributeOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<AddAttributeOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public AddAttributeOpRequestPayloadBenchmarkSubject() throws Exception {
    AddAttributeOpRequestPayload subject = AddAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
    initialize(subject, AddAttributeOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "AddAttributeOpRequestPayload";
  }
}