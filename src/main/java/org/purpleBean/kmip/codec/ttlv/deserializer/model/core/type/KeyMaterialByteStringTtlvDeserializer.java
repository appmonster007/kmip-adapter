package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyMaterialByteStringTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyMaterialByteString, KeyMaterialByteString.KeyMaterialByteStringBuilder> {

    public KeyMaterialByteStringTtlvDeserializer() {
        super(KeyMaterialByteString.kmipTag, KeyMaterialByteString.encodingType);
    }

    @Override
    protected KeyMaterialByteString.KeyMaterialByteStringBuilder createBuilder() {
        return KeyMaterialByteString.builder();
    }

    @Override
    protected void setValue(KeyMaterialByteString.KeyMaterialByteStringBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected KeyMaterialByteString build(KeyMaterialByteString.KeyMaterialByteStringBuilder builder) {
        return builder.build();
    }
}
