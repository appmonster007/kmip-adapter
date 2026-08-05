package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.Offset;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ReKeyOpRequestPayload;

/**
 * Benchmark subject for {@link ReKeyOpRequestPayload}.
 */
public class ReKeyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ReKeyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ReKeyOpRequestPayloadBenchmarkSubject}.
   */
  public ReKeyOpRequestPayloadBenchmarkSubject() throws Exception {
    ReKeyOpRequestPayload subject = ReKeyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .offset(Offset
            .builder()
            .value(100)
            .build())
        .templateAttribute(TemplateAttribute.of(java.util.List.of(), java.util.List.of()))
        .build();
    initialize(subject, ReKeyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ReKeyOpRequestPayload";
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