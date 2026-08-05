package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.NewAttribute;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SetAttributeOpRequestPayload;

/**
 * Benchmark subject for {@link SetAttributeOpRequestPayload}.
 */
public class SetAttributeOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetAttributeOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link SetAttributeOpRequestPayloadBenchmarkSubject}.
   */
  public SetAttributeOpRequestPayloadBenchmarkSubject() throws Exception {
    SetAttributeOpRequestPayload subject = SetAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("set-attr-uid-1")
            .build())
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
    initialize(subject, SetAttributeOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "SetAttributeOpRequestPayload";
  }
}
