package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v2x1.structure.response.ResponseHeader;
import org.purpleBean.kmip.model.v2x1.structure.response.ResponseMessage;

public class ResponseMessageBenchmarkSubject extends KmipBenchmarkSubject<ResponseMessage> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ResponseMessageBenchmarkSubject() throws Exception {
    KmipContext.setSpec(getSpec());
    ResponseMessage subject = ResponseMessage
        .builder()
        .responseHeader(ResponseHeader
            .builder()
            .protocolVersion(ProtocolVersion.of(2, 1))
            .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
            .batchCount(BatchCount.of(0))
            .build())
        .build();
    initialize(subject, ResponseMessage.class);
    KmipContext.clear();
  }

  @Override
  public String name() {
    return "ResponseMessage";
  }
}
