package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.ApplicationData;
import org.purpleBean.kmip.model.core.type.ApplicationNamespace;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ApplicationSpecificInformation Domain Tests")
class ApplicationSpecificInformationTest extends AbstractKmipStructureTestSuite<ApplicationSpecificInformation> implements KmipAttributeTestSuite<ApplicationSpecificInformation> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ApplicationSpecificInformation> type() {
        return ApplicationSpecificInformation.class;
    }

    @Override
    public ApplicationSpecificInformation createDefault() {
        return ApplicationSpecificInformation.builder()
                .applicationNamespace(ApplicationNamespace.of("test-namespace"))
                .applicationData(ApplicationData.of("test-data"))
                .build();
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
        assertThat(values.get(0)).isInstanceOf(ApplicationNamespace.class);
        assertThat(values.get(1)).isInstanceOf(ApplicationData.class);
    }

    @Override
    public boolean expectAlwaysPresent() {
        return false;
    }

    @Override
    public boolean expectServerInitializable() {
        return true;
    }

    @Override
    public boolean expectClientInitializable() {
        return true;
    }

    @Override
    public boolean expectClientDeletable() {
        return true;
    }

    @Override
    public boolean expectMultiInstanceAllowed() {
        return true;
    }

    @Override
    public State stateForServerModifiableTrue() {
        return State.Standard.ACTIVE.inst(); // Modifiable in any state
    }

    @Override
    public State stateForServerModifiableFalse() {
        return null; // Always modifiable
    }

    @Override
    public State stateForClientModifiableTrue() {
        return State.Standard.ACTIVE.inst(); // Modifiable in any state
    }

    @Override
    public State stateForClientModifiableFalse() {
        return null; // Always modifiable
    }

    @Override
    public AttributeValue expectedAttributeValue() {
        return AttributeValue.ofStructure(createDefault().getValue());
    }

    @Override
    public void attribute_serverModifiable_respectsState() {
        // Always true
    }

    @Override
    public void attribute_clientModifiable_respectsState() {
        // Always true
    }
}
