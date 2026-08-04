package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.NewAttribute;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ModifyAttributeOpRequestPayload;

public class ModifyAttributeOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ModifyAttributeOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ModifyAttributeOpRequestPayloadBenchmarkSubject() throws Exception {
    ModifyAttributeOpRequestPayload subject = ModifyAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
    initialize(subject, ModifyAttributeOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ModifyAttributeOpRequestPayload";
  }
}
