package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("UniqueIdentifier XML Serialization")
class UniqueIdentifierXmlTest extends AbstractXmlSerializationSuite<UniqueIdentifier> {
    @Override
    protected Class<UniqueIdentifier> type() {
        return UniqueIdentifier.class;
    }

    @Override
    protected UniqueIdentifier createDefault() {
        return new UniqueIdentifier(UniqueIdentifier.Standard.ID_PLACEHOLDER);
    }

    @Override
    protected UniqueIdentifier createVariant() {
        return new UniqueIdentifier(UniqueIdentifier.Standard.CERTIFY);
    }
}
