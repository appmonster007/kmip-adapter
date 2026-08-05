package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CreateOpRequestPayload;

/**
 * Benchmark subject for {@link CreateOpRequestPayload}.
 */
public class CreateOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CreateOpRequestPayloadBenchmarkSubject}.
   */
  public CreateOpRequestPayloadBenchmarkSubject() throws Exception {
    CreateOpRequestPayload subject = CreateOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .templateAttribute(TemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .build();
    initialize(subject, CreateOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CreateOpRequestPayload";
  }

  @Override
  public void setup() throws Exception {
    KmipContext.setSpec(spec);
  }

  @Override
  public void tearDown() {
    KmipContext.clear();
  }
}