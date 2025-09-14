package org.purpleBean.kmip.codec.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("State JSON Tests")
class StateJsonTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize standard State")
    void roundTrip_standard() {
        State original = KmipTestDataFactory.createState();
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, State.class);
    }

    @Test
    @DisplayName("Round-trip: serialize and deserialize custom State")
    void roundTrip_custom() {
        State original = KmipTestDataFactory.createCustomState();
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, State.class);
    }

    @Test
    @DisplayName("Round-trip: handle all standard states")
    void roundTrip_allStandardStates() {
        List<State> states = KmipTestDataFactory.createStates();
        for (State state : states) {
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, state, State.class);
        }
    }

    @Test
    @DisplayName("Structure: expected JSON fields present for State")
    void structure_expectFields() {
        State state = new State(State.Standard.ACTIVE);
        SerializationTestUtils.testJsonSerialization(
                jsonMapper,
                state,
                json -> {
                    SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                    assertThat(json).contains("Active");
                });
    }

    @Test
    @DisplayName("UnsupportedVersion context: State construction should fail")
    void unsupportedVersion_constructionFails() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> org.assertj.core.api.Assertions.assertThatThrownBy(
                                () -> new State(State.Standard.ACTIVE))
                        .isInstanceOf(IllegalArgumentException.class));
    }
}
