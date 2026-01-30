package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Salt;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SaltTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Salt, Salt.SaltBuilder> {

    public SaltTtlvDeserializer() {
        super(Salt.kmipTag, Salt.encodingType);
    }

    @Override
    protected Salt.SaltBuilder createBuilder() {
        return Salt.builder();
    }

    @Override
    protected void setValue(Salt.SaltBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected Salt build(Salt.SaltBuilder builder) {
        return builder.build();
    }
}
