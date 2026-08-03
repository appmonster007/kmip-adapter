package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.CreateSplitKeyOpRequestPayload;

public class CreateSplitKeyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateSplitKeyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public CreateSplitKeyOpRequestPayloadBenchmarkSubject() throws Exception {
    CreateSplitKeyOpRequestPayload subject = CreateSplitKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .splitKeyParts(SplitKeyParts.of(3))
        .splitKeyThreshold(SplitKeyThreshold.of(2))
        .splitKeyMethod(SplitKeyMethod.of(SplitKeyMethod.Standard.XOR))
        .templateAttribute(TemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .build();
    initialize(subject, CreateSplitKeyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CreateSplitKeyOpRequestPayload";
  }
}
