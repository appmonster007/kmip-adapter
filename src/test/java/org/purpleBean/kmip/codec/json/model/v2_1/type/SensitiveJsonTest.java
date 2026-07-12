package org.purpleBean.kmip.codec.json.model.v2_1.type;

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
import org.purpleBean.kmip.model.v2_1.type.Sensitive;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Sensitive Json Serialization Tests")
class SensitiveJsonTest extends AbstractJsonSerializationTestSuite<Sensitive> {

    @Override
    public Class<Sensitive> type() {
        return Sensitive.class;
    }

    @Override
    public Sensitive createDefault() {
        return Sensitive.of(true);
    }

    @Override
    public Sensitive createVariant() {
        return Sensitive.of(false);
    }
}