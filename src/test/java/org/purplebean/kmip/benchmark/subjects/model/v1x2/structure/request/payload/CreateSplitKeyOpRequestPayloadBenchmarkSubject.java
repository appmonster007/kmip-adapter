package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.SplitKeyParts;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CreateSplitKeyOpRequestPayload;

/**
 * Benchmark subject for {@link CreateSplitKeyOpRequestPayload}.
 */
public class CreateSplitKeyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateSplitKeyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link CreateSplitKeyOpRequestPayloadBenchmarkSubject}.
   */
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
