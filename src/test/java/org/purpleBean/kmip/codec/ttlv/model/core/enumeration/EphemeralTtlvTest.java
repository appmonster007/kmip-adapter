package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.Ephemeral;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Ephemeral TTLV Serialization")
class EphemeralTtlvTest extends AbstractTtlvSerializationTestSuite<Ephemeral> {
    @Override
    protected Class<Ephemeral> type() {
        return Ephemeral.class;
    }

    @Override
    protected Ephemeral createDefault() {
        return Ephemeral.Standard.DATA.inst();
    }

    @Override
    protected Ephemeral createVariant() {
        return Ephemeral.Standard.EMPTY.inst();
    }
}
