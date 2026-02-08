package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("State XML Serialization")
class StateXmlTest extends AbstractXmlSerializationTestSuite<State> {
    @Override
    public Class<State> type() {
        return State.class;
    }

    @Override
    public State createDefault() {
        return State.Standard.ACTIVE.inst();
    }

    @Override
    public State createVariant() {
        return State.Standard.DEACTIVATED.inst();
    }
}
