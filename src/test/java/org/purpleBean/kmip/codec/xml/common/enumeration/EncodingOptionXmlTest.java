package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.EncodingOption;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("EncodingOption XML Serialization")
class EncodingOptionXmlTest extends AbstractXmlSerializationTestSuite<EncodingOption> {
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
