package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.CreateOpResponsePayload;

/**
 * Benchmark subject for {@link CreateOpResponsePayload}.
 */
public class CreateOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CreateOpResponsePayloadBenchmarkSubject}.
   */
  public CreateOpResponsePayloadBenchmarkSubject() throws Exception {
    CreateOpResponsePayload subject = CreateOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .templateAttribute(TemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .build();
    initialize(subject, CreateOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "CreateOpResponsePayload";
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