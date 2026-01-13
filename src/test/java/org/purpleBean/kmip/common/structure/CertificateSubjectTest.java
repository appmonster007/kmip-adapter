package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureAttributeTestSuite;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("CertificateSubject Domain Tests")
class CertificateSubjectTest extends AbstractKmipStructureAttributeTestSuite<CertificateSubject> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_1;
    }

    @Override
    protected Class<CertificateSubject> type() {
        return CertificateSubject.class;
    }

    @Override
    protected CertificateSubject createDefault() {
        return CertificateSubject.builder()
                .certificateSubjectDistinguishedName(
                        CertificateSubjectDistinguishedName.of("CN=Test Subject")
                )
                .certificateSubjectAlternativeName(
                        CertificateSubjectAlternativeName.of("alt.subject.com")
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
        assertThat(values.get(0)).isInstanceOf(CertificateSubjectDistinguishedName.class);
        assertThat(values.get(1)).isInstanceOf(CertificateSubjectAlternativeName.class);
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