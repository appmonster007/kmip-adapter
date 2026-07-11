package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.PublicKeyAttributes;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.util.Collections;

@DisplayName("PublicKeyAttributes Xml Serialization Tests")
class PublicKeyAttributesXmlTest extends AbstractXmlSerializationTestSuite<PublicKeyAttributes> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<PublicKeyAttributes> type() {
        return PublicKeyAttributes.class;
    }

    @Override
    public PublicKeyAttributes createDefault() {
        return PublicKeyAttributes.of(Collections.emptyList());
    }

    @Override
    public PublicKeyAttributes createVariant() {
        return PublicKeyAttributes.of(Collections.emptyList());
    }
}
