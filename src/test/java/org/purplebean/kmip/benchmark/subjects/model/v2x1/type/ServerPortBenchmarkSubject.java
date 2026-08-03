package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ServerPort;

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