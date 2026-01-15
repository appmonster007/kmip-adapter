package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("State JSON Serialization")
class StateJsonTest extends AbstractJsonSerializationTestSuite<State> {
    @Override
    protected Class<State> type() {
        return State.class;
    }

    @Override
    protected State createDefault() {
        return State.Standard.ACTIVE.inst();
    }

    @Override
    protected State createVariant() {
        return State.Standard.DEACTIVATED.inst();
    }
}
