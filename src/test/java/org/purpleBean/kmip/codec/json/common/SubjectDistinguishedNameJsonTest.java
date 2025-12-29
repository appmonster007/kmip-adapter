package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.SubjectDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("SubjectDistinguishedName JSON Serialization Tests")
class SubjectDistinguishedNameJsonTest extends AbstractJsonSerializationSuite<SubjectDistinguishedName> {

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