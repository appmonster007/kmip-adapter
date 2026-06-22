package org.purpleBean.kmip.codec.xml.model.v1_2.structure.response.payload;

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
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ImportOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ImportOpResponsePayload Xml Serialization Tests")
class ImportOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<ImportOpResponsePayload> {

    @Override
    public Class<ImportOpResponsePayload> type() {
        return ImportOpResponsePayload.class;
    }

    @Override
    public ImportOpResponsePayload createDefault() {
        return ImportOpResponsePayload.of(UniqueIdentifier.builder().value("test-uid-1").build());
    }

    @Override
    public ImportOpResponsePayload createVariant() {
        return ImportOpResponsePayload.of(UniqueIdentifier.builder().value("test-uid-variant").build());
    }
}