package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerO;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateIssuerO Ttlv Serialization Tests")
class CertificateIssuerOTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateIssuerO> {

    @Override
    public Class<CertificateIssuerO> type() {
        return CertificateIssuerO.class;
    }

    @Override
    public CertificateIssuerO createDefault() {
        return CertificateIssuerO.of("default-string");
    }

    @Override
    public CertificateIssuerO createVariant() {
        return CertificateIssuerO.of("variant-string");
    }
}