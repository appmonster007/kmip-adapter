package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("ObjectGroupMember XML Serialization")
class ObjectGroupMemberXmlTest extends AbstractXmlSerializationSuite<ObjectGroupMember> {
    @Override
    protected Class<ObjectGroupMember> type() {
        return ObjectGroupMember.class;
    }

    @Override
    protected ObjectGroupMember createDefault() {
        return new ObjectGroupMember(ObjectGroupMember.Standard.GROUP_MEMBER_FRESH);
    }

    @Override
    protected ObjectGroupMember createVariant() {
        return new ObjectGroupMember(ObjectGroupMember.Standard.GROUP_MEMBER_DEFAULT);
    }
}
