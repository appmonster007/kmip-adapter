package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("ObjectGroupMember JSON Serialization")
class ObjectGroupMemberJsonTest extends AbstractJsonSerializationSuite<ObjectGroupMember> {
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
