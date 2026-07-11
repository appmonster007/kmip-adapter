package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

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
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Pkcs11Function Ttlv Serialization Tests")
class Pkcs11FunctionTtlvTest extends AbstractTtlvSerializationTestSuite<Pkcs11Function> {

    @Override
    public Class<Pkcs11Function> type() {
        return Pkcs11Function.class;
    }

    @Override
    public Pkcs11Function createDefault() {
        return Pkcs11Function.Standard.values()[0].inst();
    }

    @Override
    public Pkcs11Function createVariant() {
        return Pkcs11Function.Standard.values()[1].inst();
    }
}