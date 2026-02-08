package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DerivationData;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DerivationData XML Serialization Tests")
class DerivationDataXmlTest extends AbstractXmlSerializationTestSuite<DerivationData> {

    @Override
    public Class<DerivationData> type() {
        return DerivationData.class;
    }

    @Override
    public DerivationData createDefault() {
        return DerivationData.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    public DerivationData createVariant() {
        return DerivationData.of(new byte[]{0x04, 0x05, 0x06});
    }
}