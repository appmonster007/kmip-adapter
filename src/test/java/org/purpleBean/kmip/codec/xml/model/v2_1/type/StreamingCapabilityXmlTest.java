package org.purpleBean.kmip.codec.xml.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.StreamingCapability;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("StreamingCapability Xml Serialization Tests")
class StreamingCapabilityXmlTest extends AbstractXmlSerializationTestSuite<StreamingCapability> {

    @Override
    public Class<StreamingCapability> type() {
        return StreamingCapability.class;
    }

    @Override
    public StreamingCapability createDefault() {
        return StreamingCapability.of(true);
    }

    @Override
    public StreamingCapability createVariant() {
        return StreamingCapability.of(false);
    }
}