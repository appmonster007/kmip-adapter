package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("UniqueIdentifier XML Serialization Tests")
class UniqueIdentifierXmlTest extends AbstractXmlSerializationSuite<UniqueIdentifier> {

    @Override
    protected Class<UniqueIdentifier> type() {
        return UniqueIdentifier.class;
    }

    @Override
    protected UniqueIdentifier createDefault() {
        return UniqueIdentifier.builder().value("FIXED_STRING").build();
    }

    @Override
    protected UniqueIdentifier createVariant() {
        return UniqueIdentifier.builder().value("VARIANT_STRING").build();
    }
}
