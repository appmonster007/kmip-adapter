package org.purpleBean.kmip.model.v2_1.structure.request.payload;

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

@DisplayName("ImportOpRequestPayload Domain Tests")
class ImportOpRequestPayloadTest extends AbstractKmipStructureTestSuite<ImportOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<ImportOpRequestPayload> type() {
        return ImportOpRequestPayload.class;
    }

    @Override
    protected ImportOpRequestPayload createDefault() {
        return ImportOpRequestPayload.builder().objectType(ObjectType.Standard.CERTIFICATE.inst()).object(Certificate.of(CertificateType.Standard.X_509.inst(), CertificateValue.of(new byte[]{0x01}))).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    public int expectedMinComponentCount() {
        // TODO: Set the expected minimum number of components
        return 0;
    }

    @Override
    public void validateComponents(List<KmipDataType> values) {
        // TODO: Validate the components of the structure
        // assertThat(values).hasSize(0);
    }
}