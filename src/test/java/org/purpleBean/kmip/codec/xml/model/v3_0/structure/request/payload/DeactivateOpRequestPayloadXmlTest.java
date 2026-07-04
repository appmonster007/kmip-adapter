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
import org.purpleBean.kmip.model.v3_0.structure.request.payload.DeactivateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DeactivateOpRequestPayload Xml Serialization Tests")
class DeactivateOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<DeactivateOpRequestPayload> {

    @Override
    public Class<DeactivateOpRequestPayload> type() {
        return DeactivateOpRequestPayload.class;
    }

    @Override
    public DeactivateOpRequestPayload createDefault() {
        return DeactivateOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-1").build())
                .build();
    }

    @Override
    public DeactivateOpRequestPayload createVariant() {
        return DeactivateOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-2").build())
                .build();
    }
}