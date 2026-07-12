package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

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
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ImportOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ImportOpRequestPayload Ttlv Serialization Tests")
class ImportOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<ImportOpRequestPayload> {

    @Override
    public Class<ImportOpRequestPayload> type() {
        return ImportOpRequestPayload.class;
    }
    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }



    @Override
    public ImportOpRequestPayload createDefault() {
        return ImportOpRequestPayload.builder().objectType(ObjectType.Standard.CERTIFICATE.inst()).object(Certificate.of(CertificateType.Standard.X_509.inst(), CertificateValue.of(new byte[]{0x01}))).build();
    }

    @Override
    public ImportOpRequestPayload createVariant() {
        return ImportOpRequestPayload.builder().objectType(ObjectType.Standard.CERTIFICATE.inst()).object(Certificate.of(CertificateType.Standard.X_509.inst(), CertificateValue.of(new byte[]{0x02}))).build();
    }
}