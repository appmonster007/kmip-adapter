package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.CertificateIssuerAlternativeName;
import org.purpleBean.kmip.common.CertificateIssuerDistinguishedName;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureAttributeSuite;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("CertificateIssuer Domain Tests")
class CertificateIssuerTest extends AbstractKmipStructureAttributeSuite<CertificateIssuer> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_1;
    }

    @Override
    protected Class<CertificateIssuer> type() {
        return CertificateIssuer.class;
    }

    @Override
    protected CertificateIssuer createDefault() {
        return CertificateIssuer.builder()
                .certificateIssuerDistinguishedName(
                        CertificateIssuerDistinguishedName.of("CN=Test Issuer")
                )
                .certificateIssuerAlternativeName(
                        CertificateIssuerAlternativeName.of("alt.issuer.com")
                )
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
        assertThat(values.get(0)).isInstanceOf(CertificateIssuerDistinguishedName.class);
        assertThat(values.get(1)).isInstanceOf(CertificateIssuerAlternativeName.class);
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