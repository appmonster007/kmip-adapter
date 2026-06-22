package org.purpleBean.kmip.model.v3_0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CredentialInformation Domain Tests")
class CredentialInformationTest extends AbstractKmipStructureTestSuite<CredentialInformation> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<CredentialInformation> type() {
        return CredentialInformation.class;
    }

    @Override
    protected CredentialInformation createDefault() {
        return CredentialInformation.builder()
                .credentialType(CredentialType.of(CredentialType.Standard.USERNAME_AND_PASSWORD))
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    public int expectedMinComponentCount() {
        return 1;
    }

    @Override
    public void validateComponents(List<KmipDataType> values) {
        assertThat(values).hasSize(1);
        assertThat(values.get(0)).isInstanceOf(CredentialType.class);
    }
}