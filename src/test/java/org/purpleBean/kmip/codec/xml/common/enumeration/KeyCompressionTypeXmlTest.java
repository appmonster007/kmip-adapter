package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("KeyCompressionType XML Serialization")
class KeyCompressionTypeXmlTest extends AbstractXmlSerializationSuite<KeyCompressionType> {
    @Override
    protected Class<KeyCompressionType> type() {
        return KeyCompressionType.class;
    }

    @Override
    protected KeyCompressionType createDefault() {
        return new KeyCompressionType(KeyCompressionType.Standard.EC_PUBLIC_KEY_TYPE_UNCOMPRESSED);
    }

    @Override
    protected KeyCompressionType createVariant() {
        return new KeyCompressionType(KeyCompressionType.Standard.EC_PUBLIC_KEY_TYPE_X9_62_COMPRESSED_PRIME);
    }
}
