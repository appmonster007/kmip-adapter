package org.purpleBean.kmip.codec.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.util.List;

@DisplayName("State XML Tests")
class StateXmlTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize standard State")
    void roundTrip_standard() {
        State original = KmipTestDataFactory.createState();
        SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, State.class);
    }

    @Test
    @DisplayName("Round-trip: serialize and deserialize custom State")
    void roundTrip_custom() {
        State original = KmipTestDataFactory.createCustomState();
        SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, State.class);
    }

    @Test
    @DisplayName("Round-trip: handle all standard states")
    void roundTrip_allStandardStates() {
        List<State> states = KmipTestDataFactory.createStates();
        for (State state : states) {
            SerializationTestUtils.performXmlRoundTrip(xmlMapper, state, State.class);
        }
    }

    @Test
    @DisplayName("UnsupportedVersion context: State XML serialization should fail")
    void unsupportedVersion_xmlSerializationFails() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> org.assertj.core.api.Assertions.assertThatThrownBy(
                                () -> xmlMapper.writeValueAsString(new State(State.Standard.ACTIVE)))
                        .isInstanceOf(Exception.class));
    }
}
