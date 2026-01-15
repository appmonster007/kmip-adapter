package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyCompressionType XML Serialization")
class KeyCompressionTypeXmlTest extends AbstractXmlSerializationTestSuite<KeyCompressionType> {
    @Override
    protected Class<KeyCompressionType> type() {
        return KeyCompressionType.class;
    }

    @Override
    protected KeyCompressionType createDefault() {
        return KeyCompressionType.Standard.EC_PUBLIC_KEY_TYPE_UNCOMPRESSED.inst();
    }

    @Override
    protected KeyCompressionType createVariant() {
        return KeyCompressionType.Standard.EC_PUBLIC_KEY_TYPE_X9_62_COMPRESSED_PRIME.inst();
    }
}
