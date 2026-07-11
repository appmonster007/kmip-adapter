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
import org.purpleBean.kmip.model.v2_1.type.AsynchronousCapability;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AsynchronousCapability Json Serialization Tests")
class AsynchronousCapabilityJsonTest extends AbstractJsonSerializationTestSuite<AsynchronousCapability> {

    @Override
    public Class<AsynchronousCapability> type() {
        return AsynchronousCapability.class;
    }

    @Override
    public AsynchronousCapability createDefault() {
        return AsynchronousCapability.of(true);
    }

    @Override
    public AsynchronousCapability createVariant() {
        return AsynchronousCapability.of(false);
    }
}