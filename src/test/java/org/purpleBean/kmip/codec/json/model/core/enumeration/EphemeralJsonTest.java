package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.Ephemeral;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Ephemeral JSON Serialization")
class EphemeralJsonTest extends AbstractJsonSerializationTestSuite<Ephemeral> {
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
