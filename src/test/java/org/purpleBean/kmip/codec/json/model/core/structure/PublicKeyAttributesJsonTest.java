package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.PublicKeyAttributes;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.util.Collections;

@DisplayName("PublicKeyAttributes Json Serialization Tests")
class PublicKeyAttributesJsonTest extends AbstractJsonSerializationTestSuite<PublicKeyAttributes> {

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
