package org.purpleBean.kmip.codec.xml.model.core.type;

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
import org.purpleBean.kmip.model.core.type.Pkcs11ReturnCode;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Pkcs11ReturnCode Xml Serialization Tests")
class Pkcs11ReturnCodeXmlTest extends AbstractXmlSerializationTestSuite<Pkcs11ReturnCode> {

    @Override
    public Class<Pkcs11ReturnCode> type() {
        return Pkcs11ReturnCode.class;
    }

    @Override
    public Pkcs11ReturnCode createDefault() {
        return Pkcs11ReturnCode.of(1);
    }

    @Override
    public Pkcs11ReturnCode createVariant() {
        return Pkcs11ReturnCode.of(2);
    }
}