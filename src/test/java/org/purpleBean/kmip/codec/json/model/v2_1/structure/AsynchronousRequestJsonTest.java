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
import org.purpleBean.kmip.model.v2_1.structure.AsynchronousRequest;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AsynchronousRequest Json Serialization Tests")
class AsynchronousRequestJsonTest extends AbstractJsonSerializationTestSuite<AsynchronousRequest> {

    @Override
    public Class<AsynchronousRequest> type() {
        return AsynchronousRequest.class;
    }

    @Override
    public AsynchronousRequest createDefault() {
        return AsynchronousRequest.builder().build();
    }

    @Override
    public AsynchronousRequest createVariant() {
        return AsynchronousRequest.builder().build();
    }
}