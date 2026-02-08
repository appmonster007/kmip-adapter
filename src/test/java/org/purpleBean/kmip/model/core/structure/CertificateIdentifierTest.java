package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.Issuer;
import org.purpleBean.kmip.model.core.type.SerialNumber;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;
import org.purpleBean.kmip.test.suite.KmipAttributeTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CertificateIdentifier Domain Tests")
class CertificateIdentifierTest extends AbstractKmipStructureTestSuite<CertificateIdentifier> implements KmipAttributeTestSuite<CertificateIdentifier> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_1;
    }

    @Override
    protected Class<CertificateIdentifier> type() {
        return CertificateIdentifier.class;
    }

    @Override
    public CertificateIdentifier createDefault() {
        return CertificateIdentifier.builder()
                .issuer(Issuer.of("test-issuer"))
                .serialNumber(SerialNumber.of("test-serial"))
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
        assertThat(values.get(0)).isInstanceOf(Issuer.class);
        assertThat(values.get(1)).isInstanceOf(SerialNumber.class);
    }

    @Override
    public boolean expectAlwaysPresent() {
        return true;
    }

    @Override
    public boolean expectServerInitializable() {
        return true;
    }

    @Override
    public boolean expectClientInitializable() {
        return false;
    }

    @Override
    public boolean expectClientDeletable() {
        return false;
    }

    @Override
    public boolean expectMultiInstanceAllowed() {
        return false;
    }

    @Override
    public State stateForServerModifiableTrue() {
        return null; // Not modifiable by server
    }

    @Override
    public State stateForServerModifiableFalse() {
        return State.Standard.ACTIVE.inst(); // Never modifiable by server
    }

    @Override
    public State stateForClientModifiableTrue() {
        return null; // Not modifiable by client
    }

    @Override
    public State stateForClientModifiableFalse() {
        return State.Standard.ACTIVE.inst(); // Never modifiable by client
    }

    @Override
    public AttributeValue expectedAttributeValue() {
        return AttributeValue.ofStructure(createDefault().getValue());
    }

    @Override
    public void attribute_serverModifiable_respectsState() {
        // Always false
    }

    @Override
    public void attribute_clientModifiable_respectsState() {
        // Always false
    }
}
