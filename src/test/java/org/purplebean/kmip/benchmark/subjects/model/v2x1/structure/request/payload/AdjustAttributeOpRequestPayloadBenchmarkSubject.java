package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.enumeration.AdjustmentType;
import org.purplebean.kmip.model.v2x1.structure.AttributeReference;
import org.purplebean.kmip.model.v2x1.structure.request.payload.AdjustAttributeOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.AdjustmentValue;

/**
 * Benchmark subject for {@link AdjustAttributeOpRequestPayload}.
 */
public class AdjustAttributeOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<AdjustAttributeOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link AdjustAttributeOpRequestPayloadBenchmarkSubject}.
   */
  public AdjustAttributeOpRequestPayloadBenchmarkSubject() throws Exception {
    AdjustAttributeOpRequestPayload subject = AdjustAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("adj-attr-uid-1")
            .build())
        .attributeReference(AttributeReference
            .builder()
            .attributeName(AttributeName.of("Usage Limits Count"))
            .build())
        .adjustmentType(AdjustmentType.Standard.INCREMENT.inst())
        .adjustmentValue(AdjustmentValue.ofLongInteger(1L))
        .build();
    initialize(subject, AdjustAttributeOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "AdjustAttributeOpRequestPayload";
  }
}
