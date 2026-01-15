package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ObjectGroupMember TTLV Serialization")
class ObjectGroupMemberTtlvTest extends AbstractTtlvSerializationTestSuite<ObjectGroupMember> {
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
