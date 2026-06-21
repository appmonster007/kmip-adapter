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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetConstraintsOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SetConstraintsOpResponsePayload Xml Serialization Tests")
class SetConstraintsOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<SetConstraintsOpResponsePayload> {

    @Override
    public Class<SetConstraintsOpResponsePayload> type() {
        return SetConstraintsOpResponsePayload.class;
    }

    @Override
    public SetConstraintsOpResponsePayload createDefault() {
        return SetConstraintsOpResponsePayload.builder().build();
    }

    @Override
    public SetConstraintsOpResponsePayload createVariant() {
        return SetConstraintsOpResponsePayload.builder().build();
    }
}