package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

@DisplayName("State Domain Tests")
class StateTest extends AbstractKmipEnumerationSuite<State> {

    @Override
    protected Class<State> type() { return State.class; }

    @Override
    protected State createDefault() { return new State(State.Standard.ACTIVE); }

    @Override
    protected State createEqualToDefault() { return new State(State.Standard.ACTIVE); }

    @Override
    protected State createDifferentFromDefault() { return new State(State.Standard.DEACTIVATED); }

    @Override
    protected EncodingType expectedEncodingType() { return EncodingType.ENUMERATION; }
}
