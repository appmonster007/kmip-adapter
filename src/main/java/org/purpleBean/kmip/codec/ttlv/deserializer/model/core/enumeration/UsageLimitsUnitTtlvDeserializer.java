package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;

import java.io.IOException;
import java.nio.ByteBuffer;

public class UsageLimitsUnitTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UsageLimitsUnit, UsageLimitsUnit.UsageLimitsUnitBuilder> {

    public UsageLimitsUnitTtlvDeserializer() {
        super(UsageLimitsUnit.kmipTag, UsageLimitsUnit.encodingType);
    }

    @Override
    protected UsageLimitsUnit.UsageLimitsUnitBuilder createBuilder() {
        return UsageLimitsUnit.builder();
    }

    @Override
    protected void setValue(UsageLimitsUnit.UsageLimitsUnitBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(UsageLimitsUnit.fromValue(value));
    }

    @Override
    protected UsageLimitsUnit build(UsageLimitsUnit.UsageLimitsUnitBuilder builder) {
        return builder.build();
    }
}
