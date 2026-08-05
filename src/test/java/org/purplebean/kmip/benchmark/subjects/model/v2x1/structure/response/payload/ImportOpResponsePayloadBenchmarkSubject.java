package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ImportOpResponsePayload;

/**
 * Benchmark subject for {@link ImportOpResponsePayload}.
 */
public class ImportOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ImportOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link ImportOpResponsePayloadBenchmarkSubject}.
   */
  public ImportOpResponsePayloadBenchmarkSubject() throws Exception {
    ImportOpResponsePayload subject = ImportOpResponsePayload.of(UniqueIdentifier
        .builder()
        .value("test-uid-1")
        .build());
    initialize(subject, ImportOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "ImportOpResponsePayload";
  }
}