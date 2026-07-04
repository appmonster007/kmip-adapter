package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NewAttribute Xml Serialization Tests")
class NewAttributeXmlTest extends AbstractXmlSerializationTestSuite<NewAttribute> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<NewAttribute> type() {
        return NewAttribute.class;
    }

    @Override
    public NewAttribute createDefault() {
        return NewAttribute.builder()
                .attribute(CryptographicAlgorithm.Standard.AES.inst())
                .build();
    }

    @Override
    public NewAttribute createVariant() {
        return NewAttribute.builder()
                .attribute(CryptographicAlgorithm.Standard.RSA.inst())
                .build();
    }
}
