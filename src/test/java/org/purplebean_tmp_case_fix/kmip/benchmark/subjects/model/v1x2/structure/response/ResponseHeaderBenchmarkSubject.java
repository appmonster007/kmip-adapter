package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.model.v1x2.structure.response.ResponseHeader;

public class ResponseHeaderBenchmarkSubject extends KmipBenchmarkSubject<ResponseHeader> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public ResponseHeaderBenchmarkSubject() throws Exception {
    KmipContext.setSpec(spec);
    ResponseHeader subject = ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
        .timeStamp(TimeStamp.of(OffsetDateTime.now(ZoneOffset.UTC)))
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
