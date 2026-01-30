package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ExtensionName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ExtensionNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ExtensionName, ExtensionName.ExtensionNameBuilder> {

    public ExtensionNameTtlvDeserializer() {
        super(ExtensionName.kmipTag, ExtensionName.encodingType);
    }

    @Override
    protected ExtensionName.ExtensionNameBuilder createBuilder() {
        return ExtensionName.builder();
    }

    @Override
    protected void setValue(ExtensionName.ExtensionNameBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected ExtensionName build(ExtensionName.ExtensionNameBuilder builder) {
        return builder.build();
    }
}
