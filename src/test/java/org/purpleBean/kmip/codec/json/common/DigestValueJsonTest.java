package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DigestValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("DigestValue JSON Serialization Tests")
class DigestValueJsonTest extends AbstractJsonSerializationSuite<DigestValue> {

    @Override
    protected Class<DigestValue> type() {
        return DigestValue.class;
    }

    @Override
    protected DigestValue createDefault() {
        return DigestValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected DigestValue createVariant() {
        return DigestValue.of(new byte[]{0x04, 0x05, 0x06});
    }
}