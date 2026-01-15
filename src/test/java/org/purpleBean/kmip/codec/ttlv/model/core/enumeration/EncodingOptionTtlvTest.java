package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("EncodingOption TTLV Serialization")
class EncodingOptionTtlvTest extends AbstractTtlvSerializationTestSuite<EncodingOption> {
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
