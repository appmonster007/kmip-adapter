package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ObjectGroupMember JSON Serialization")
class ObjectGroupMemberJsonTest extends AbstractJsonSerializationTestSuite<ObjectGroupMember> {
    @Override
    protected Class<ObjectGroupMember> type() {
        return ObjectGroupMember.class;
    }

    @Override
    protected ObjectGroupMember createDefault() {
        return ObjectGroupMember.Standard.GROUP_MEMBER_FRESH.inst();
    }

    @Override
    protected ObjectGroupMember createVariant() {
        return ObjectGroupMember.Standard.GROUP_MEMBER_DEFAULT.inst();
    }
}
