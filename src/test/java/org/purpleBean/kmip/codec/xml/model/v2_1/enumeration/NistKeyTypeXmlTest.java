package org.purpleBean.kmip.codec.xml.model.v2_1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.enumeration.NistKeyType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NistKeyType XML Serialization")
class NistKeyTypeXmlTest extends AbstractXmlSerializationTestSuite<NistKeyType> {
    @Override
    public Class<NistKeyType> type() {
        return NistKeyType.class;
    }

    @Override
    public NistKeyType createDefault() {
        return NistKeyType.Standard.PRIVATE_SIGNATURE_KEY.inst();
    }

    @Override
    public NistKeyType createVariant() {
        return NistKeyType.Standard.PUBLIC_SIGNATURE_VERIFICATION_KEY.inst();
    }
}
