package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.structure.PrivateKeyAttributes;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.util.Collections;

@DisplayName("PrivateKeyAttributes Ttlv Serialization Tests")
class PrivateKeyAttributesTtlvTest extends AbstractTtlvSerializationTestSuite<PrivateKeyAttributes> {

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
