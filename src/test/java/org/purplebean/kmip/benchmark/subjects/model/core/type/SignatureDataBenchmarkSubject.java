package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.SignatureData;

/**
 * Benchmark subject for {@link SignatureData}.
 */
public class SignatureDataBenchmarkSubject extends KmipBenchmarkSubject<SignatureData> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link SignatureDataBenchmarkSubject}.
   */
  public SignatureDataBenchmarkSubject() throws Exception {
    byte[] data = "test signature data".getBytes();
    SignatureData signatureData = SignatureData.of(ByteBuffer.wrap(data));
    initialize(signatureData, SignatureData.class);
  }

  @Override
  public String name() {
    return "SignatureData";
  }

}
