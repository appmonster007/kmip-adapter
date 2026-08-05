package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SymmetricKey;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.request.payload.RegisterOpRequestPayload;

/**
 * Benchmark subject for {@link RegisterOpRequestPayload}.
 */
public class RegisterOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RegisterOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link RegisterOpRequestPayloadBenchmarkSubject}.
   */
  public RegisterOpRequestPayloadBenchmarkSubject() throws Exception {
    RegisterOpRequestPayload subject = RegisterOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .attributes(Attributes
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .object(SymmetricKey
            .builder()
            .keyBlock(KeyBlock
                .builder()
                .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
                .build())
            .build())
        .build();
    initialize(subject, RegisterOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "RegisterOpRequestPayload";
  }
}