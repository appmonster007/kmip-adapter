package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("DerivationMethod XML Serialization")
class DerivationMethodXmlTest extends AbstractXmlSerializationSuite<DerivationMethod> {
    @Override
    protected Class<DerivationMethod> type() {
        return DerivationMethod.class;
    }

    @Override
    protected DerivationMethod createDefault() {
        return new DerivationMethod(DerivationMethod.Standard.PBKDF2);
    }

    @Override
    protected DerivationMethod createVariant() {
        return new DerivationMethod(DerivationMethod.Standard.HASH);
    }
}
