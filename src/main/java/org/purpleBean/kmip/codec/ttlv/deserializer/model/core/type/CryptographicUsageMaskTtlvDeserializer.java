package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CryptographicUsageMaskTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CryptographicUsageMask, CryptographicUsageMask.CryptographicUsageMaskBuilder> {

    public CryptographicUsageMaskTtlvDeserializer() {
        super(CryptographicUsageMask.kmipTag, CryptographicUsageMask.encodingType);
    }

    @Override
    protected CryptographicUsageMask.CryptographicUsageMaskBuilder createBuilder() {
        return CryptographicUsageMask.builder();
    }

    @Override
    protected void setValue(CryptographicUsageMask.CryptographicUsageMaskBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected CryptographicUsageMask build(CryptographicUsageMask.CryptographicUsageMaskBuilder builder) {
        return builder.build();
    }
}
