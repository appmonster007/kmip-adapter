package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

@DisplayName("ContactInformation Domain Tests")
class ContactInformationTest extends AbstractKmipDataTypeTestSuite<ContactInformation> implements KmipAttributeTestSuite<ContactInformation> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ContactInformation> type() {
        return ContactInformation.class;
    }

    @Override
    public ContactInformation createDefault() {
        return ContactInformation.of("test-contact");
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
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
        return false;
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
        return AttributeValue.ofTextString("test-contact");
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
