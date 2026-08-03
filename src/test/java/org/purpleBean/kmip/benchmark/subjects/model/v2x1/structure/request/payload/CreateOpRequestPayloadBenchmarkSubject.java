package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.v2x1.structure.request.payload.CreateOpRequestPayload;

public class CreateOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CreateOpRequestPayloadBenchmarkSubject() throws Exception {
    CreateOpRequestPayload subject = CreateOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .attributes(org.purplebean.kmip.model.v2x1.structure.Attributes
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
    initialize(subject, CreateOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CreateOpRequestPayload";
  }
}