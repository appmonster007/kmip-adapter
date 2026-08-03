package org.purpleBean.kmip.benchmark.subjects.model.core.structure.request;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;

public class SimpleRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SimpleRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public SimpleRequestPayloadBenchmarkSubject() throws Exception {
    SimpleRequestPayload subject = SimpleRequestPayload
        .builder()
        .build();
    initialize(subject, SimpleRequestPayload.class);
  }

  @Override
  public String name() {
    return "SimpleRequestPayload";
  }

  @Override
  public void setup() throws Exception {
    KmipContext.setSpec(spec);
  }

  @Override
  public void tearDown() {
    KmipContext.clear();
  }
}