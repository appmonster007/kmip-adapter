package org.purpleBean.kmip.codec.xml.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.MaximumItems;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.LocateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LocateOpRequestPayload Xml Serialization Tests")
class LocateOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<LocateOpRequestPayload> {

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
        return LocateOpRequestPayload.builder()
                .maximumItems(MaximumItems.of(10))
                .storageStatusMask(StorageStatusMask.of(1))
                .objectGroupMember(ObjectGroupMember.of(ObjectGroupMember.Standard.GROUP_MEMBER_FRESH))
                .attribute(Attribute.of(AttributeName.of("test-attribute"), AttributeValue.ofTextString("test-value")))
                .build();
    }

    @Override
    public LocateOpRequestPayload createVariant() {
        return LocateOpRequestPayload.builder()
                .maximumItems(MaximumItems.of(20))
                .storageStatusMask(StorageStatusMask.of(2))
                .objectGroupMember(ObjectGroupMember.of(ObjectGroupMember.Standard.GROUP_MEMBER_DEFAULT))
                .attribute(Attribute.of(AttributeName.of("test-attribute-2"), AttributeValue.ofTextString("test-value-2")))
                .build();
    }
}
