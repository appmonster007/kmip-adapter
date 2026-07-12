package org.purpleBean.kmip.codec.json.model.core.enumeration;

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
import org.purpleBean.kmip.model.core.enumeration.Pkcs11Function;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.util.Set;

@DisplayName("Pkcs11Function Json Serialization Tests")
class Pkcs11FunctionJsonTest extends AbstractJsonSerializationTestSuite<Pkcs11Function> {

    @Override
    public Class<Pkcs11Function> type() {
        return Pkcs11Function.class;
    }

    @Override
    public Pkcs11Function createDefault() {
        return Pkcs11Function.register(0x80000021, "X-Json-Default", Set.of(KmipSpec.UnknownVersion)).inst();
    }

    @Override
    public Pkcs11Function createVariant() {
        return Pkcs11Function.register(0x80000022, "X-Json-Variant", Set.of(KmipSpec.UnknownVersion)).inst();
    }
}