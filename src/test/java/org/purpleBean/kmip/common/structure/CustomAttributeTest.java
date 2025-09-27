package org.purpleBean.kmip.common.structure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureAttributeSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@DisplayName("CustomAttribute Domain Tests")
class CustomAttributeTest extends AbstractKmipStructureAttributeSuite<CustomAttribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<CustomAttribute> type() {
        return CustomAttribute.class;
    }

    @Override
    protected CustomAttribute createDefault() {
        State state = new State(State.Standard.ACTIVE);
        return CustomAttribute.of("x-custom-state", state);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 2;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        Assertions.assertThat(values.get(0)).isInstanceOf(AttributeName.class);
        Assertions.assertThat(values.get(1)).isInstanceOf(AttributeValue.class);
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
        return true;
    }

    @Override
    protected boolean expectMultiInstanceAllowed() {
        return true;
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
    protected void attrStruct_serverModifiable_respectsState() {
    }

    @Override
    protected void attrStruct_clientModifiable_respectsState() {
    }
}
