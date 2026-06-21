package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.Pkcs11OpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Pkcs11OpRequestPayload Xml Serialization Tests")
class Pkcs11OpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<Pkcs11OpRequestPayload> {

    @Override
    public Class<Pkcs11OpRequestPayload> type() {
        return Pkcs11OpRequestPayload.class;
    }

    @Override
    public Pkcs11OpRequestPayload createDefault() {
        return Pkcs11OpRequestPayload.builder().build();
    }

    @Override
    public Pkcs11OpRequestPayload createVariant() {
        return Pkcs11OpRequestPayload.builder().build();
    }
}