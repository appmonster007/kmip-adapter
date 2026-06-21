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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LoginOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LoginOpRequestPayload Xml Serialization Tests")
class LoginOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<LoginOpRequestPayload> {

    @Override
    public Class<LoginOpRequestPayload> type() {
        return LoginOpRequestPayload.class;
    }

    @Override
    public LoginOpRequestPayload createDefault() {
        return LoginOpRequestPayload.builder().build();
    }

    @Override
    public LoginOpRequestPayload createVariant() {
        return LoginOpRequestPayload.builder().build();
    }
}