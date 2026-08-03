package org.purplebean.kmip.codec.xml.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ObjectGroupMember;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.MaximumItems;
import org.purplebean.kmip.model.core.type.StorageStatusMask;
import org.purplebean.kmip.model.v1x2.structure.request.payload.LocateOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LocateOpRequestPayload Xml Serialization Tests")
class LocateOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<LocateOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<LocateOpRequestPayload> type() {
    return LocateOpRequestPayload.class;
  }

  @Override
  public LocateOpRequestPayload createDefault() {
    return LocateOpRequestPayload
        .builder()
        .maximumItems(MaximumItems.of(10))
        .storageStatusMask(StorageStatusMask.of(1))
        .objectGroupMember(ObjectGroupMember.of(ObjectGroupMember.Standard.GROUP_MEMBER_FRESH))
        .attribute(Attribute.of(AttributeName.of("test-attribute"),
            AttributeValue.ofTextString("test-value")))
        .build();
  }

  @Override
  public LocateOpRequestPayload createVariant() {
    return LocateOpRequestPayload
        .builder()
        .maximumItems(MaximumItems.of(20))
        .storageStatusMask(StorageStatusMask.of(2))
        .objectGroupMember(ObjectGroupMember.of(ObjectGroupMember.Standard.GROUP_MEMBER_DEFAULT))
        .attribute(Attribute.of(AttributeName.of("test-attribute-2"),
            AttributeValue.ofTextString("test-value-2")))
        .build();
  }
}
