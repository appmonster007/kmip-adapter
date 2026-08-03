package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.MaximumItems;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.LocateOpRequestPayload;

public class LocateOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<LocateOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public LocateOpRequestPayloadBenchmarkSubject() throws Exception {
    LocateOpRequestPayload subject = LocateOpRequestPayload
        .builder()
        .maximumItems(MaximumItems.of(10))
        .storageStatusMask(StorageStatusMask.of(1))
        .objectGroupMember(ObjectGroupMember.of(ObjectGroupMember.Standard.GROUP_MEMBER_FRESH))
        .attribute(Attribute.of(AttributeName.of("test-attribute"),
            AttributeValue.ofTextString("test-value")))
        .build();
    initialize(subject, LocateOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "LocateOpRequestPayload";
  }
}
