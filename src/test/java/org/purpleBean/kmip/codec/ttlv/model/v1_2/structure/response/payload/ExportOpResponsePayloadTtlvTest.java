package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

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
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ExportOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ExportOpResponsePayload Ttlv Serialization Tests")
class ExportOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<ExportOpResponsePayload> {

    @Override
    public Class<ExportOpResponsePayload> type() {
        return ExportOpResponsePayload.class;
    }
    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }



    @Override
    public ExportOpResponsePayload createDefault() {
        return ExportOpResponsePayload.builder().objectType(ObjectType.Standard.CERTIFICATE.inst()).uniqueIdentifier(org.purpleBean.kmip.model.core.type.UniqueIdentifier.of("cert-1")).object(Certificate.of(CertificateType.Standard.X_509.inst(), CertificateValue.of(new byte[]{0x01}))).build();
    }

    @Override
    public ExportOpResponsePayload createVariant() {
        return ExportOpResponsePayload.builder().objectType(ObjectType.Standard.CERTIFICATE.inst()).uniqueIdentifier(org.purpleBean.kmip.model.core.type.UniqueIdentifier.of("cert-2")).object(Certificate.of(CertificateType.Standard.X_509.inst(), CertificateValue.of(new byte[]{0x02}))).build();
    }
}