package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("State TTLV Serialization")
class StateTtlvTest extends AbstractTtlvSerializationSuite<State> {
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
