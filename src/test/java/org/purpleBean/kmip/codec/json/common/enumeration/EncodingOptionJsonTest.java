package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.EncodingOption;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("EncodingOption JSON Serialization")
class EncodingOptionJsonTest extends AbstractJsonSerializationSuite<EncodingOption> {
    @Override
    protected Class<EncodingOption> type() {
        return EncodingOption.class;
    }

    @Override
    protected EncodingOption createDefault() {
        return new EncodingOption(EncodingOption.Standard.PLACEHOLDER_1);
    }

    @Override
    protected EncodingOption createVariant() {
        return new EncodingOption(EncodingOption.Standard.PLACEHOLDER_2);
    }
}
