package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;

import java.io.IOException;
import java.nio.ByteBuffer;

public class UsageLimitsCountTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UsageLimitsCount, UsageLimitsCount.UsageLimitsCountBuilder> {

    public UsageLimitsCountTtlvDeserializer() {
        super(UsageLimitsCount.kmipTag, UsageLimitsCount.encodingType);
    }

    @Override
    protected UsageLimitsCount.UsageLimitsCountBuilder createBuilder() {
        return UsageLimitsCount.builder();
    }

    @Override
    protected void setValue(UsageLimitsCount.UsageLimitsCountBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Long.class));
    }

    @Override
    protected UsageLimitsCount build(UsageLimitsCount.UsageLimitsCountBuilder builder) {
        return builder.build();
    }
}
