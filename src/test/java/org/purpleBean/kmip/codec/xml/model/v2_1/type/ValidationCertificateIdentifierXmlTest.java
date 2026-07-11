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
import org.purpleBean.kmip.model.v2_1.type.ValidationCertificateIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationCertificateIdentifier Xml Serialization Tests")
class ValidationCertificateIdentifierXmlTest extends AbstractXmlSerializationTestSuite<ValidationCertificateIdentifier> {

    @Override
    public Class<ValidationCertificateIdentifier> type() {
        return ValidationCertificateIdentifier.class;
    }

    @Override
    public ValidationCertificateIdentifier createDefault() {
        return ValidationCertificateIdentifier.of("default-string");
    }

    @Override
    public ValidationCertificateIdentifier createVariant() {
        return ValidationCertificateIdentifier.of("variant-string");
    }
}