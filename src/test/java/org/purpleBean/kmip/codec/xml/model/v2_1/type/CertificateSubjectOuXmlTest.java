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
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectOu;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSubjectOu Xml Serialization Tests")
class CertificateSubjectOuXmlTest extends AbstractXmlSerializationTestSuite<CertificateSubjectOu> {

    @Override
    public Class<CertificateSubjectOu> type() {
        return CertificateSubjectOu.class;
    }

    @Override
    public CertificateSubjectOu createDefault() {
        return CertificateSubjectOu.of("default-string");
    }

    @Override
    public CertificateSubjectOu createVariant() {
        return CertificateSubjectOu.of("variant-string");
    }
}