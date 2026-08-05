package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SymmetricKey;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RegisterOpRequestPayload;

/**
 * Benchmark subject for {@link RegisterOpRequestPayload}.
 */
public class RegisterOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RegisterOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link RegisterOpRequestPayloadBenchmarkSubject}.
   */
  public RegisterOpRequestPayloadBenchmarkSubject() throws Exception {
    RegisterOpRequestPayload subject = RegisterOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .templateAttribute(TemplateAttribute.of(java.util.List.of(), java.util.List.of()))
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

  @Override
  public void setup() throws Exception {
    KmipContext.setSpec(spec);
  }

  @Override
  public void tearDown() {
    KmipContext.clear();
  }
}