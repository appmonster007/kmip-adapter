package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.EncodingOption;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("EncodingOption XML Serialization")
class EncodingOptionXmlTest extends AbstractXmlSerializationSuite<EncodingOption> {
    @Override
    protected Class<EncodingOption> type() {
        return EncodingOption.class;
    }

    @Override
    protected EncodingOption createDefault() {
        return new EncodingOption(EncodingOption.Standard.NO_ENCODING);
    }

    @Override
    protected EncodingOption createVariant() {
        return new EncodingOption(EncodingOption.Standard.TTLV_ENCODING);
    }
}
