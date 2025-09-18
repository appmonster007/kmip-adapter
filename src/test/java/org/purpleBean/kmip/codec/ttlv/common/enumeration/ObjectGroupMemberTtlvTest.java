package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ObjectGroupMember TTLV Serialization")
class ObjectGroupMemberTtlvTest extends AbstractTtlvSerializationSuite<ObjectGroupMember> {
    @Override
    protected Class<ObjectGroupMember> type() {
        return ObjectGroupMember.class;
    }

    @Override
    protected ObjectGroupMember createDefault() {
        return new ObjectGroupMember(ObjectGroupMember.Standard.PLACEHOLDER_1);
    }

    @Override
    protected ObjectGroupMember createVariant() {
        return new ObjectGroupMember(ObjectGroupMember.Standard.PLACEHOLDER_2);
    }
}
