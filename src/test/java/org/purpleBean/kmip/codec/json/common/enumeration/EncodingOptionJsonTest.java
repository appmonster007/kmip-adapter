package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.EncodingOption;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("EncodingOption JSON Serialization")
class EncodingOptionJsonTest extends AbstractJsonSerializationTestSuite<EncodingOption> {
    @Override
    protected Class<EncodingOption> type() {
        return EncodingOption.class;
    }

    @Override
    protected EncodingOption createDefault() {
        return EncodingOption.Standard.NO_ENCODING.inst();
    }

    @Override
    protected EncodingOption createVariant() {
        return EncodingOption.Standard.TTLV_ENCODING.inst();
    }
}
