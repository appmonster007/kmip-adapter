package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.model.v2x1.structure.response.ResponseHeader;

public class ResponseHeaderBenchmarkSubject extends KmipBenchmarkSubject<ResponseHeader> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ResponseHeaderBenchmarkSubject() throws Exception {
    KmipContext.setSpec(getSpec());
    ResponseHeader subject = ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(2, 1))
        .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
        .batchCount(BatchCount.of(1))
        .build();
    initialize(subject, ResponseHeader.class);
    KmipContext.clear();
  }

  @Override
  public String name() {
    return "ResponseHeader";
  }
}
