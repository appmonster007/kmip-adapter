package org.purplebean.kmip.benchmark.subjects.model.v3x0.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.type.ServerHashedPassword;

/**
 * Benchmark subject for {@link ServerHashedPassword}.
 */
public class ServerHashedPasswordBenchmarkSubject
    extends KmipBenchmarkSubject<ServerHashedPassword> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ServerHashedPasswordBenchmarkSubject}.
   */
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