package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

import java.util.List;

@DisplayName("RandomNumberGenerator Domain Tests")
class RandomNumberGeneratorTest extends AbstractKmipStructureTestSuite<RandomNumberGenerator> implements KmipAttributeTestSuite<RandomNumberGenerator> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<RandomNumberGenerator> type() {
        return RandomNumberGenerator.class;
    }

    @Override
    public RandomNumberGenerator createDefault() {
        return RandomNumberGenerator.of(RngParameters.of(RngAlgorithm.Standard.UNSPECIFIED.inst()));
    }

    @Override
    public EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    public int expectedMinComponentCount() {
        return 0;
    }

    @Override
    public void validateComponents(List<KmipDataType> values) {
        // TODO: Validate component as per the structure design
        // Example:
        // assertThat(values.get(0)).isInstanceOf(AlternativeNameValue.class);
        // assertThat(values.get(1)).isInstanceOf(AlternativeNameType.class);
    }

    @Override
    public boolean expectAlwaysPresent() {
        return false;  // TODO: Adjust as needed
    }

    @Override
    public boolean expectServerInitializable() {
        return true;  // TODO: Adjust as needed
    }

    @Override
    public boolean expectClientInitializable() {
        return true;  // TODO: Adjust as needed
    }

    @Override
    public boolean expectClientDeletable() {
        return false;  // TODO: Adjust as needed
    }

    @Override
    public boolean expectMultiInstanceAllowed() {
        return false;
    }

    @Override
    public State stateForServerModifiableTrue() {
        return null;  // TODO: Adjust as needed
    }

    @Override
    public State stateForServerModifiableFalse() {
        return null;  // TODO: Adjust as needed
    }

    @Override
    public State stateForClientModifiableTrue() {
        return null;  // TODO: Adjust as needed
    }

    @Override
    public State stateForClientModifiableFalse() {
        return null; // TODO: Adjust as needed
    }

    @Override
    public AttributeValue expectedAttributeValue() {
        return AttributeValue.ofStructure(createDefault().getValue());
    }

    // AttributeName is a v1.x concept; RandomNumberGenerator is V2.1+
    @Override
    public void attribute_getAttributeName_returnsExpectedValue() {
        // getAttributeName() is not applicable for V2.1+ attributes
    }

    // Attribute round-trip via AttributeName is not applicable for V2.1+ types
    @Override
    public void attribute_roundTrip() {
        // getAttributeName() is not applicable for V2.1+ attributes
    }

    @Override
    public void attribute_serverModifiable_respectsState() {
        // TODO: Adjust as needed
    }

    @Override
    public void attribute_clientModifiable_respectsState() {
        // TODO: Adjust as needed
    }
}