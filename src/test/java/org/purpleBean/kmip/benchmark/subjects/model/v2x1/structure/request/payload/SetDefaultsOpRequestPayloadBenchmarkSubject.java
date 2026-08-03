package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.DefaultsInformation;
import org.purpleBean.kmip.model.v2x1.structure.ObjectDefaults;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.SetDefaultsOpRequestPayload;

public class SetDefaultsOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetDefaultsOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public SetDefaultsOpRequestPayloadBenchmarkSubject() throws Exception {
    SetDefaultsOpRequestPayload subject = SetDefaultsOpRequestPayload
        .builder()
        .defaultsInformation(DefaultsInformation.of(java.util.List.of(ObjectDefaults
            .builder()
            .objectType(ObjectType.Standard.CERTIFICATE.inst())
            .attributes(Attributes.of(java.util.List.of()))
            .build())))
        .build();
    initialize(subject, SetDefaultsOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "SetDefaultsOpRequestPayload";
  }
}