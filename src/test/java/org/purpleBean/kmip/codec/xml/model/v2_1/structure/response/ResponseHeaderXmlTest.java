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
import org.purpleBean.kmip.model.v2_1.structure.response.ResponseHeader;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ResponseHeader Xml Serialization Tests")
class ResponseHeaderXmlTest extends AbstractXmlSerializationTestSuite<ResponseHeader> {

    @Override
    public Class<ResponseHeader> type() {
        return ResponseHeader.class;
    }

    @Override
    public ResponseHeader createDefault() {
        return ResponseHeader.builder().build();
    }

    @Override
    public ResponseHeader createVariant() {
        return ResponseHeader.builder().build();
    }
}