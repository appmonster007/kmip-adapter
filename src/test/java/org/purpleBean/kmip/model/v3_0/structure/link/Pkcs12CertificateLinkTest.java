package org.purpleBean.kmip.model.v3_0.structure.link;

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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Pkcs12CertificateLink Domain Tests")
class Pkcs12CertificateLinkTest extends AbstractKmipStructureTestSuite<Pkcs12CertificateLink> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V3_0;
    }

    @Override
    protected Class<Pkcs12CertificateLink> type() {
        return Pkcs12CertificateLink.class;
    }

    @Override
    protected Pkcs12CertificateLink createDefault() {
        // TODO: Create a default instance of the structure
        return Pkcs12CertificateLink.of(UniqueIdentifier.of("test-id"));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    public int expectedMinComponentCount() {
        // TODO: Set the expected minimum number of components
        return 1;
    }

    @Override
    public void validateComponents(List<KmipDataType> values) {
        // TODO: Validate the components of the structure
        assertThat(values).hasSize(1);
    }
}