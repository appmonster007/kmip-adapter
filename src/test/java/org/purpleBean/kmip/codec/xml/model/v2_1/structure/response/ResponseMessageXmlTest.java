package org.purpleBean.kmip.codec.xml.model.v2_1.structure.response;

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
import org.purpleBean.kmip.model.v2_1.structure.response.ResponseMessage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ResponseMessage Xml Serialization Tests")
class ResponseMessageXmlTest extends AbstractXmlSerializationTestSuite<ResponseMessage> {

    @Override
    public Class<ResponseMessage> type() {
        return ResponseMessage.class;
    }

    @Override
    public ResponseMessage createDefault() {
        return ResponseMessage.builder().build();
    }

    @Override
    public ResponseMessage createVariant() {
        return ResponseMessage.builder().build();
    }
}