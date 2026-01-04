package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeAttributeSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ProtectStopDate Domain Tests")
class ProtectStopDateTest extends AbstractKmipDataTypeAttributeSuite<ProtectStopDate> {

    private static final OffsetDateTime PAST_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    private static final OffsetDateTime FUTURE_TIME = OffsetDateTime.now().plusDays(1);

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ProtectStopDate> type() {
        return ProtectStopDate.class;
    }

    @Override
    protected ProtectStopDate createDefault() {
        return ProtectStopDate.builder().value(PAST_TIME).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.DATE_TIME;
    }

    @Override
    protected boolean expectAlwaysPresent() {
        return false;
    }

    @Override
    protected boolean expectServerInitializable() {
        return true;
    }

    @Override
    protected boolean expectClientInitializable() {
        return true;
    }

    @Override
    protected boolean expectClientDeletable() {
        return false;
    }

    @Override
    protected boolean expectMultiInstanceAllowed() {
        return false;
    }

    @Override
    protected State stateForServerModifiableTrue() {
        return new State(State.Standard.PRE_ACTIVE);
    }

    @Override
    protected State stateForServerModifiableFalse() {
        return new State(State.Standard.DEACTIVATED);
    }

    @Override
    protected State stateForClientModifiableTrue() {
        return new State(State.Standard.PRE_ACTIVE);
    }

    @Override
    protected State stateForClientModifiableFalse() {
        return new State(State.Standard.DEACTIVATED);
    }

    @Test
    @DisplayName("attribute_serverModifiable_respectsStateAndDate")
    @Override
    protected void attribute_serverModifiable_respectsState() {
        ProtectStopDate futureDate = ProtectStopDate.builder().value(FUTURE_TIME).build();
        assertThat(futureDate.isServerModifiable(stateForServerModifiableTrue())).isTrue();

        ProtectStopDate pastDate = createDefault();
        assertThat(pastDate.isServerModifiable(stateForServerModifiableTrue())).isFalse();
        assertThat(pastDate.isServerModifiable(stateForServerModifiableFalse())).isFalse();
    }

    @Test
    @DisplayName("attribute_clientModifiable_respectsStateAndDate")
    @Override
    protected void attribute_clientModifiable_respectsState() {
        ProtectStopDate futureDate = ProtectStopDate.builder().value(FUTURE_TIME).build();
        assertThat(futureDate.isClientModifiable(stateForClientModifiableTrue())).isTrue();

        ProtectStopDate pastDate = createDefault();
        assertThat(pastDate.isClientModifiable(stateForClientModifiableTrue())).isFalse();
        assertThat(pastDate.isClientModifiable(stateForClientModifiableFalse())).isFalse();
    }
}