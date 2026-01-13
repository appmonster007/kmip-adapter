package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeAttributeTestSuite;

@DisplayName("KeyValuePresent Domain Tests")
class KeyValuePresentTest extends AbstractKmipDataTypeAttributeTestSuite<KeyValuePresent> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<KeyValuePresent> type() {
        return KeyValuePresent.class;
    }

    @Override
    protected KeyValuePresent createDefault() {
        return KeyValuePresent.of(Boolean.FALSE);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BOOLEAN;
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
        return null;
    }

    @Override
    protected State stateForServerModifiableFalse() {
        return null;
    }

    @Override
    protected State stateForClientModifiableTrue() {
        return null;
    }

    @Override
    protected State stateForClientModifiableFalse() {
        return null;
    }

    @Override
    protected void attribute_serverModifiable_respectsState() {
    }

    @Override
    protected void attribute_clientModifiable_respectsState() {
    }
}