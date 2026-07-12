package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.structure.PublicKeyAttributes;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.util.Collections;

@DisplayName("PublicKeyAttributes Ttlv Serialization Tests")
class PublicKeyAttributesTtlvTest extends AbstractTtlvSerializationTestSuite<PublicKeyAttributes> {

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
