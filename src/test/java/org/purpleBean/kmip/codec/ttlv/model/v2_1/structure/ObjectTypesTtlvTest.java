package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.ObjectTypes;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ObjectTypes Ttlv Serialization Tests")
class ObjectTypesTtlvTest extends AbstractTtlvSerializationTestSuite<ObjectTypes> {

    @Override
    public Class<ObjectTypes> type() {
        return ObjectTypes.class;
    }

    @Override
    public ObjectTypes createDefault() {
        return ObjectTypes.builder().build();
    }

    @Override
    public ObjectTypes createVariant() {
        return ObjectTypes.builder().build();
    }
}