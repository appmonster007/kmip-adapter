package org.purpleBean.kmip.codec.xml.model.v3_0.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.ObliterateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ObliterateOpRequestPayload Xml Serialization Tests")
class ObliterateOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<ObliterateOpRequestPayload> {

    @Override
    public Class<ObliterateOpRequestPayload> type() {
        return ObliterateOpRequestPayload.class;
    }

    @Override
    public ObliterateOpRequestPayload createDefault() {
        return ObliterateOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-1").build())
                .build();
    }

    @Override
    public ObliterateOpRequestPayload createVariant() {
        return ObliterateOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-2").build())
                .build();
    }
}