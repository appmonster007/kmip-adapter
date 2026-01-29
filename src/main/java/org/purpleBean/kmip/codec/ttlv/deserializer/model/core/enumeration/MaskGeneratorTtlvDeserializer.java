package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.MaskGenerator;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MaskGeneratorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MaskGenerator, MaskGenerator.MaskGeneratorBuilder> {

    public MaskGeneratorTtlvDeserializer() {
        super(MaskGenerator.kmipTag, MaskGenerator.encodingType);
    }

    @Override
    protected MaskGenerator.MaskGeneratorBuilder createBuilder() {
        return MaskGenerator.builder();
    }

    @Override
    protected void setValue(MaskGenerator.MaskGeneratorBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(MaskGenerator.fromValue(value));
    }

    @Override
    protected MaskGenerator build(MaskGenerator.MaskGeneratorBuilder builder) {
        return builder.build();
    }
}
