package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.request.payload.ObliterateOpRequestPayload;

/**
 * Benchmark subject for {@link ObliterateOpRequestPayload}.
 */
public class ObliterateOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ObliterateOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link ObliterateOpRequestPayloadBenchmarkSubject}.
   */
  public ObliterateOpRequestPayloadBenchmarkSubject() throws Exception {
    ObliterateOpRequestPayload subject = ObliterateOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
    initialize(subject, ObliterateOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ObliterateOpRequestPayload";
  }
}