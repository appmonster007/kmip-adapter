package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.NotifyOpRequestPayload;

public class NotifyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<NotifyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public NotifyOpRequestPayloadBenchmarkSubject() throws Exception {
    NotifyOpRequestPayload subject = NotifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attribute(Attribute.of(AttributeName.of("test-attribute"),
            AttributeValue.ofTextString("test-value")))
        .build();
    initialize(subject, NotifyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "NotifyOpRequestPayload";
  }
}
