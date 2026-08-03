package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.response.payload.DecryptOpResponsePayload;

public class DecryptOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DecryptOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public DecryptOpResponsePayloadBenchmarkSubject() throws Exception {
    DecryptOpResponsePayload subject = DecryptOpResponsePayload
        .builder()
        .build();
    initialize(subject, DecryptOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "DecryptOpResponsePayload";
  }
}