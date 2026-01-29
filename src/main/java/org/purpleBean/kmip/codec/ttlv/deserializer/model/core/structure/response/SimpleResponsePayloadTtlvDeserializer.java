package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure.response;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SimpleResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SimpleResponsePayload, SimpleResponsePayload.SimpleResponsePayloadBuilder> {

    public SimpleResponsePayloadTtlvDeserializer() {
        super(SimpleResponsePayload.kmipTag, SimpleResponsePayload.encodingType);
    }

    @Override
    protected SimpleResponsePayload.SimpleResponsePayloadBuilder createBuilder() {
        return SimpleResponsePayload.builder();
    }

    @Override
    protected void setValue(SimpleResponsePayload.SimpleResponsePayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // No fields to set
    }

    @Override
    protected SimpleResponsePayload build(SimpleResponsePayload.SimpleResponsePayloadBuilder builder) {
        return builder.build();
    }
}
