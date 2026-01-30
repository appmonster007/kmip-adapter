package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ExtensionType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ExtensionTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ExtensionType, ExtensionType.ExtensionTypeBuilder> {

    public ExtensionTypeTtlvDeserializer() {
        super(ExtensionType.kmipTag, ExtensionType.encodingType);
    }

    @Override
    protected ExtensionType.ExtensionTypeBuilder createBuilder() {
        return ExtensionType.builder();
    }

    @Override
    protected void setValue(ExtensionType.ExtensionTypeBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected ExtensionType build(ExtensionType.ExtensionTypeBuilder builder) {
        return builder.build();
    }
}
