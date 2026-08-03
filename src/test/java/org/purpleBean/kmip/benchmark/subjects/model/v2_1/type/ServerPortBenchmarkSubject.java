package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.ServerPort;

public class ServerPortBenchmarkSubject extends KmipBenchmarkSubject<ServerPort> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ServerPortBenchmarkSubject() throws Exception {
    ServerPort subject = ServerPort.of(123);
    initialize(subject, ServerPort.class);
  }

  @Override
  public String name() {
    return "ServerPort";
  }
}