package org.purpleBean.kmip.model.core.type.vendor;

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
import java.util.Set;

import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("TtlvDataType Domain Tests")
class TtlvDataTypeTest extends AbstractKmipDataTypeTestSuite<TtlvDataType> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<TtlvDataType> type() {
        return TtlvDataType.class;
    }

    @Override
    protected TtlvDataType createDefault() {
        return TtlvDataType.builder()
                .kmipTag(UniqueIdentifier.kmipTag)
                .encodingType(UniqueIdentifier.encodingType)
                .value("default-string")
                .build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}