package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.NistKeyType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NistKeyType XML Serialization")
class NistKeyTypeXmlTest extends AbstractXmlSerializationTestSuite<NistKeyType> {
    @Override
    protected Class<NistKeyType> type() {
        return NistKeyType.class;
    }

    @Override
    protected NistKeyType createDefault() {
        return NistKeyType.Standard.PRIVATE_SIGNATURE_KEY.inst();
    }

    @Override
    protected NistKeyType createVariant() {
        return NistKeyType.Standard.PUBLIC_SIGNATURE_VERIFICATION_KEY.inst();
    }
}
