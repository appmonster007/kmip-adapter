package org.purpleBean.kmip.codec.json.model.core.type;

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
import org.purpleBean.kmip.model.core.type.ReplaceExisting;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ReplaceExisting Json Serialization Tests")
class ReplaceExistingJsonTest extends AbstractJsonSerializationTestSuite<ReplaceExisting> {

    @Override
    public Class<ReplaceExisting> type() {
        return ReplaceExisting.class;
    }

    @Override
    public ReplaceExisting createDefault() {
        return ReplaceExisting.of(true);
    }

    @Override
    public ReplaceExisting createVariant() {
        return ReplaceExisting.of(false);
    }
}