package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.Ephemeral;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Ephemeral XML Serialization")
class EphemeralXmlTest extends AbstractXmlSerializationTestSuite<Ephemeral> {
    @Override
    protected Class<Ephemeral> type() {
        return Ephemeral.class;
    }

    @Override
    protected Ephemeral createDefault() {
        return new Ephemeral(Ephemeral.Standard.DATA);
    }

    @Override
    protected Ephemeral createVariant() {
        return new Ephemeral(Ephemeral.Standard.EMPTY);
    }
}
