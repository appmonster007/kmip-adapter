package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.v2_1.structure.request.RequestHeader;
import org.purpleBean.kmip.model.v2_1.structure.request.RequestMessage;

public class RequestMessageBenchmarkSubject extends KmipBenchmarkSubject<RequestMessage> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public RequestMessageBenchmarkSubject() throws Exception {
    KmipContext.setSpec(getSpec());
    RequestMessage subject = RequestMessage
        .builder()
        .requestHeader(RequestHeader
            .builder()
            .protocolVersion(ProtocolVersion.of(2, 1))
            .batchCount(BatchCount.of(0))
            .build())
        .build();
    initialize(subject, RequestMessage.class);
    KmipContext.clear();
  }

  @Override
  public String name() {
    return "RequestMessage";
  }
}
