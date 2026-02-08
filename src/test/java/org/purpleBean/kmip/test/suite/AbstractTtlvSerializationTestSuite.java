package org.purpleBean.kmip.test.suite;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.nio.ByteBuffer;

/**
 * Reusable TTLV serialization/deserialization test suite for KMIP objects.
 */
@DisplayName("Abstract TTLV Serialization Suite")
public abstract class AbstractTtlvSerializationTestSuite<T> extends BaseKmipTest implements KmipSerializationTestSuite<T, TtlvMapper, ByteBuffer> {

    @Override
    public TtlvMapper getMapper() {
        return getTtlvMapper();
    }

    @Override
    public ByteBuffer serialize(T object) throws Exception {
        return getMapper().writeValueAsByteBuffer(object);
    }

    @Override
    public T deserialize(ByteBuffer serialized) throws Exception {
        return getMapper().readValue(serialized, type());
    }

    @Override
    public void withKmipSpec(KmipSpec spec, Runnable operation) {
        super.withKmipSpec(spec, operation);
    }
}
