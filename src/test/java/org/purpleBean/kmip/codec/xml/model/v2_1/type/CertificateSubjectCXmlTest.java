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
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectC;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSubjectC Xml Serialization Tests")
class CertificateSubjectCXmlTest extends AbstractXmlSerializationTestSuite<CertificateSubjectC> {

    @Override
    public Class<CertificateSubjectC> type() {
        return CertificateSubjectC.class;
    }

    @Override
    public CertificateSubjectC createDefault() {
        return CertificateSubjectC.of("default-string");
    }

    @Override
    public CertificateSubjectC createVariant() {
        return CertificateSubjectC.of("variant-string");
    }
}