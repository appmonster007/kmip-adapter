package org.purpleBean.kmip.codec.xml.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerSt;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateIssuerSt Xml Serialization Tests")
class CertificateIssuerStXmlTest extends AbstractXmlSerializationTestSuite<CertificateIssuerSt> {

    @Override
    public Class<CertificateIssuerSt> type() {
        return CertificateIssuerSt.class;
    }

    @Override
    public CertificateIssuerSt createDefault() {
        return CertificateIssuerSt.of("default-string");
    }

    @Override
    public CertificateIssuerSt createVariant() {
        return CertificateIssuerSt.of("variant-string");
    }
}