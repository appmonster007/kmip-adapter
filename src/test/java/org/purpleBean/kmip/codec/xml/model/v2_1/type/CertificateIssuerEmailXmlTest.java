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
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerEmail;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateIssuerEmail Xml Serialization Tests")
class CertificateIssuerEmailXmlTest extends AbstractXmlSerializationTestSuite<CertificateIssuerEmail> {

    @Override
    public Class<CertificateIssuerEmail> type() {
        return CertificateIssuerEmail.class;
    }

    @Override
    public CertificateIssuerEmail createDefault() {
        return CertificateIssuerEmail.of("default-string");
    }

    @Override
    public CertificateIssuerEmail createVariant() {
        return CertificateIssuerEmail.of("variant-string");
    }
}