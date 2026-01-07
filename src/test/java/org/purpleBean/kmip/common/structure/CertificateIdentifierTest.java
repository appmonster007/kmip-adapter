package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.common.SerialNumber;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureAttributeSuite;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("CertificateIdentifier Domain Tests")
class CertificateIdentifierTest extends AbstractKmipStructureAttributeSuite<CertificateIdentifier> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_1;
    }

    @Override
    protected Class<CertificateIdentifier> type() {
        return CertificateIdentifier.class;
    }

    @Override
    protected CertificateIdentifier createDefault() {
        return CertificateIdentifier.builder()
                .issuer(Issuer.of("CN=Test Issuer"))
                .serialNumber(SerialNumber.of("12345"))
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
    protected boolean expectAlwaysPresent() {
        return true;
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
    protected void attrStruct_serverModifiable_respectsState() {
    }

    @Override
    protected void attrStruct_clientModifiable_respectsState() {
    }
}