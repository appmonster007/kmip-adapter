package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.ImportOpResponsePayload;

public class ImportOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ImportOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

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