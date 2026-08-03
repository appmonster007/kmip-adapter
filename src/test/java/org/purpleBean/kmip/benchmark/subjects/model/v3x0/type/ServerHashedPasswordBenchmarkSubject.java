package org.purpleBean.kmip.benchmark.subjects.model.v3x0.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3x0.type.ServerHashedPassword;

public class ServerHashedPasswordBenchmarkSubject
    extends KmipBenchmarkSubject<ServerHashedPassword> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ServerHashedPasswordBenchmarkSubject() throws Exception {
    ServerHashedPassword subject =
        ServerHashedPassword.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(subject, ServerHashedPassword.class);
  }

  @Override
  public String name() {
    return "ServerHashedPassword";
  }
}