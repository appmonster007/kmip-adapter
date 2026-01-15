package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SubjectDistinguishedName TTLV Serialization Tests")
class SubjectDistinguishedNameTtlvTest extends AbstractTtlvSerializationTestSuite<SubjectDistinguishedName> {

    @Override
    protected Class<SubjectDistinguishedName> type() {
        return SubjectDistinguishedName.class;
    }

    @Override
    protected SubjectDistinguishedName createDefault() {
        return SubjectDistinguishedName.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected SubjectDistinguishedName createVariant() {
        return SubjectDistinguishedName.of(new byte[]{0x04, 0x05, 0x06});
    }
}