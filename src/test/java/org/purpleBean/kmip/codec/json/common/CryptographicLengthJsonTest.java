package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CryptographicLength;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;


@DisplayName("CryptographicLength JSON Serialization Tests")
class CryptographicLengthJsonTest extends AbstractJsonSerializationSuite<CryptographicLength> {

    @Override
    protected Class<CryptographicLength> type() {
        return CryptographicLength.class;
    }

    @Override
    protected CryptographicLength createDefault() {
        return CryptographicLength.of(256);
    }

    @Override
    protected CryptographicLength createVariant() {
        return CryptographicLength.of(512);
    }
}
