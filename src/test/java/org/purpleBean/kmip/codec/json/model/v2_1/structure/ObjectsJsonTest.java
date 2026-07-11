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
import org.purpleBean.kmip.model.v2_1.structure.Objects;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Objects Json Serialization Tests")
class ObjectsJsonTest extends AbstractJsonSerializationTestSuite<Objects> {

    @Override
    public Class<Objects> type() {
        return Objects.class;
    }

    @Override
    public Objects createDefault() {
        return Objects.builder().build();
    }

    @Override
    public Objects createVariant() {
        return Objects.builder().build();
    }
}