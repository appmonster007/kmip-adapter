package org.purpleBean.kmip.codec.xml.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.Pkcs11OpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11ReturnCode;

@DisplayName("Pkcs11OpResponsePayload Xml Serialization Tests")
class Pkcs11OpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<Pkcs11OpResponsePayload> {

    @Override
    public Class<Pkcs11OpResponsePayload> type() {
        return Pkcs11OpResponsePayload.class;
    }

    @Override
    public Pkcs11OpResponsePayload createDefault() {
        return Pkcs11OpResponsePayload.builder().pkcs11ReturnCode(Pkcs11ReturnCode.of(0)).build();
    }

    @Override
    public Pkcs11OpResponsePayload createVariant() {
        return Pkcs11OpResponsePayload.builder().pkcs11ReturnCode(Pkcs11ReturnCode.of(1)).build();
    }
}