package org.purpleBean.kmip.model.core.structure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.AttributeValue;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureAttributeTestSuite;

import java.util.List;

@DisplayName("CustomAttribute Domain Tests")
class CustomAttributeTest extends AbstractKmipStructureAttributeTestSuite<CustomAttribute> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CustomAttribute> type() {
        return CustomAttribute.class;
    }

    @Override
    protected CustomAttribute createDefault() {
        return CustomAttribute.of("x-custom-state", AttributeValueEnumeration.of(1));
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
        return State.Standard.ACTIVE.inst();
    }

    @Override
    protected State stateForServerModifiableFalse() {
        return State.Standard.PRE_ACTIVE.inst();
    }

    @Override
    protected State stateForClientModifiableTrue() {
        return State.Standard.ACTIVE.inst();
    }

    @Override
    protected State stateForClientModifiableFalse() {
        return State.Standard.PRE_ACTIVE.inst();
    }


    @Override
    protected void attrStruct_serverModifiable_respectsState() {
    }

    @Override
    protected void attrStruct_clientModifiable_respectsState() {
    }
}
