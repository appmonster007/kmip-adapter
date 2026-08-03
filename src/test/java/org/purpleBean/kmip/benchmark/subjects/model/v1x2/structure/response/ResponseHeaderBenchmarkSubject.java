package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v1x2.structure.response.ResponseHeader;

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
