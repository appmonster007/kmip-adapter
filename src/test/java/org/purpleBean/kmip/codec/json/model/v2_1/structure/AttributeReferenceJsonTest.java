package org.purpleBean.kmip.codec.json.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.AttributeReference;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AttributeReference Json Serialization Tests")
class AttributeReferenceJsonTest extends AbstractJsonSerializationTestSuite<AttributeReference> {

    @Override
    public Class<AttributeReference> type() {
        return AttributeReference.class;
    }

    @Override
    public AttributeReference createDefault() {
        return AttributeReference.builder().build();
    }

    @Override
    public AttributeReference createVariant() {
        return AttributeReference.builder().build();
    }
}