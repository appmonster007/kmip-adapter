package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("State TTLV Serialization")
class StateTtlvTest extends AbstractTtlvSerializationTestSuite<State> {
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
