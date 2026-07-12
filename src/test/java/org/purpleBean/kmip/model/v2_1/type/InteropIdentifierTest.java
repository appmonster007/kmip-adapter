package org.purpleBean.kmip.model.v2_1.type;

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

import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;
import org.purpleBean.kmip.model.v2_1.type.InteropIdentifier;

@DisplayName("InteropIdentifier Domain Tests")
class InteropIdentifierTest extends AbstractKmipDataTypeTestSuite<InteropIdentifier> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    protected Class<InteropIdentifier> type() {
        return InteropIdentifier.class;
    }

    @Override
    public InteropIdentifier createDefault() {
        return InteropIdentifier.of("default-string");
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}