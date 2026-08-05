package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetAttributeOpResponsePayload;

/**
 * Benchmark subject for {@link SetAttributeOpResponsePayload}.
 */
public class SetAttributeOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetAttributeOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link SetAttributeOpResponsePayloadBenchmarkSubject}.
   */
  public SetAttributeOpResponsePayloadBenchmarkSubject() throws Exception {
    SetAttributeOpResponsePayload subject = SetAttributeOpResponsePayload.of(UniqueIdentifier
        .builder()
        .value("test-uid-1")
        .build());
    initialize(subject, SetAttributeOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "SetAttributeOpResponsePayload";
  }
}