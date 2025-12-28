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

@DisplayName("CompromiseDate Domain Tests")
class CompromiseDateTest extends AbstractKmipDataTypeAttributeSuite<CompromiseDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CompromiseDate> type() {
        return CompromiseDate.class;
    }

    @Override
    protected CompromiseDate createDefault() {
        return CompromiseDate.builder().value(FIXED_TIME).build();
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
        return false;
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
        // This method is not used since isServerModifiable always returns false
        return new State(State.Standard.ACTIVE);
    }

    @Override
    protected State stateForServerModifiableFalse() {
        return new State(State.Standard.ACTIVE);
    }

    @Override
    protected State stateForClientModifiableTrue() {
        // This method is not used since isClientModifiable always returns false
        return new State(State.Standard.ACTIVE);
    }

    @Override
    protected State stateForClientModifiableFalse() {
        return new State(State.Standard.ACTIVE);
    }
    
    @Test
    @DisplayName("attribute_serverModifiable_isAlwaysFalse")
    @Override
    protected void attribute_serverModifiable_respectsState() {
        CompromiseDate date = createDefault();
        assertThat(date.isServerModifiable(stateForServerModifiableTrue())).isFalse();
        assertThat(date.isServerModifiable(stateForServerModifiableFalse())).isFalse();
    }

    @Test
    @DisplayName("attribute_clientModifiable_isAlwaysFalse")
    @Override
    protected void attribute_clientModifiable_respectsState() {
        CompromiseDate date = createDefault();
        assertThat(date.isClientModifiable(stateForClientModifiableTrue())).isFalse();
        assertThat(date.isClientModifiable(stateForClientModifiableFalse())).isFalse();
    }
}