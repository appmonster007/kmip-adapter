package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.HashOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("HashOpRequestPayload Json Serialization Tests")
class HashOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<HashOpRequestPayload> {

    @Override
    public Class<HashOpRequestPayload> type() {
        return HashOpRequestPayload.class;
    }

    @Override
    public HashOpRequestPayload createDefault() {
        return HashOpRequestPayload.builder().cryptographicParameters(CryptographicParameters.builder().cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst()).build()).build();
    }

    @Override
    public HashOpRequestPayload createVariant() {
        return HashOpRequestPayload.builder().cryptographicParameters(CryptographicParameters.builder().cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst()).build()).build();
    }
}