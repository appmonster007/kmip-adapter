package org.purpleBean.kmip.codec.json.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerC;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuerC Json Serialization Tests")
class CertificateIssuerCJsonTest extends AbstractJsonSerializationTestSuite<CertificateIssuerC> {

    @Override
    public Class<CertificateIssuerC> type() {
        return CertificateIssuerC.class;
    }

    @Override
    public CertificateIssuerC createDefault() {
        return CertificateIssuerC.of("default-string");
    }

    @Override
    public CertificateIssuerC createVariant() {
        return CertificateIssuerC.of("variant-string");
    }
}