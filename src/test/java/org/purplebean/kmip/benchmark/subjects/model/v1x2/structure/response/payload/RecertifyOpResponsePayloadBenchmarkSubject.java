package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RecertifyOpResponsePayload;

/**
 * Benchmark subject for {@link RecertifyOpResponsePayload}.
 */
public class RecertifyOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RecertifyOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link RecertifyOpResponsePayloadBenchmarkSubject}.
   */
  public RecertifyOpResponsePayloadBenchmarkSubject() throws Exception {
    RecertifyOpResponsePayload subject = RecertifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .templateAttribute(TemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .build();
    initialize(subject, RecertifyOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "RecertifyOpResponsePayload";
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