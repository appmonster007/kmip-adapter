package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.Pkcs11OutputParameters;

/**
 * Benchmark subject for {@link Pkcs11OutputParameters}.
 */
public class Pkcs11OutputParametersBenchmarkSubject
    extends KmipBenchmarkSubject<Pkcs11OutputParameters> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link Pkcs11OutputParametersBenchmarkSubject}.
   */
  public Pkcs11OutputParametersBenchmarkSubject() throws Exception {
    Pkcs11OutputParameters subject =
        Pkcs11OutputParameters.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(subject, Pkcs11OutputParameters.class);
  }

  @Override
  public String name() {
    return "Pkcs11OutputParameters";
  }
}