package org.purpleBean.kmip.codec.xml.model.v2_1.enumeration;

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
import org.purpleBean.kmip.model.v2_1.enumeration.Pkcs11Function;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.util.Set;

@DisplayName("Pkcs11Function Xml Serialization Tests")
class Pkcs11FunctionXmlTest extends AbstractXmlSerializationTestSuite<Pkcs11Function> {

    @Override
    public Class<Pkcs11Function> type() {
        return Pkcs11Function.class;
    }

    @Override
    public Pkcs11Function createDefault() {
        return Pkcs11Function.register(0x80000041, "X-Xml-Default", Set.of(KmipSpec.UnknownVersion)).inst();
    }

    @Override
    public Pkcs11Function createVariant() {
        return Pkcs11Function.register(0x80000042, "X-Xml-Variant", Set.of(KmipSpec.UnknownVersion)).inst();
    }
}
