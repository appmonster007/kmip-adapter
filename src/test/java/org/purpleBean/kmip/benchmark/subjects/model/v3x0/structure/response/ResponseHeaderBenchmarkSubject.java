package org.purpleBean.kmip.benchmark.subjects.model.v3x0.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v3x0.structure.response.ResponseHeader;

public class ResponseHeaderBenchmarkSubject extends KmipBenchmarkSubject<ResponseHeader> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public ResponseHeaderBenchmarkSubject() throws Exception {
    ResponseHeader subject = ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(3, 0))
        .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
        .build();
    initialize(subject, ResponseHeader.class);
  }

  @Override
  public String name() {
    return "ResponseHeader";
  }
}