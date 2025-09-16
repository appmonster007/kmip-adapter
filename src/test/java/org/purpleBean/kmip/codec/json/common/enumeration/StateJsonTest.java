package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("State JSON Serialization Tests")
class StateJsonTest extends AbstractJsonSerializationSuite<State> {

    @Override
    protected Class<State> type() {
        return State.class;
    }

    @Override
    protected State createDefault() {
        return new State(State.Standard.ACTIVE);
    }

    @Override
    protected State createVariant() {
        return new State(State.Standard.DEACTIVATED);
    }
}
