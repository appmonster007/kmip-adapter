package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.PrivateKeyAttributes;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.util.Collections;

@DisplayName("PrivateKeyAttributes Json Serialization Tests")
class PrivateKeyAttributesJsonTest extends AbstractJsonSerializationTestSuite<PrivateKeyAttributes> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<PrivateKeyAttributes> type() {
        return PrivateKeyAttributes.class;
    }

    @Override
    public PrivateKeyAttributes createDefault() {
        return PrivateKeyAttributes.of(Collections.emptyList());
    }

    @Override
    public PrivateKeyAttributes createVariant() {
        return PrivateKeyAttributes.of(Collections.emptyList());
    }
}
