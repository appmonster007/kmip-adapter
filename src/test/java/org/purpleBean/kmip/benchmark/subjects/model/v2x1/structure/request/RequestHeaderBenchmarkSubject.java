package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.v2x1.structure.request.RequestHeader;

public class RequestHeaderBenchmarkSubject extends KmipBenchmarkSubject<RequestHeader> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public RequestHeaderBenchmarkSubject() throws Exception {
    KmipContext.setSpec(getSpec());
    RequestHeader subject = RequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(2, 1))
        .batchCount(BatchCount.of(1))
        .build();
    initialize(subject, RequestHeader.class);
    KmipContext.clear();
  }

  @Override
  public String name() {
    return "RequestHeader";
  }
}
