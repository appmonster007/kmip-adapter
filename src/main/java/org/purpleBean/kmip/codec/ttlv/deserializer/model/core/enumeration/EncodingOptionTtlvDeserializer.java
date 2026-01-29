package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;

import java.io.IOException;
import java.nio.ByteBuffer;

public class EncodingOptionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<EncodingOption, EncodingOption.EncodingOptionBuilder> {

    public EncodingOptionTtlvDeserializer() {
        super(EncodingOption.kmipTag, EncodingOption.encodingType);
    }

    @Override
    protected EncodingOption.EncodingOptionBuilder createBuilder() {
        return EncodingOption.builder();
    }

    @Override
    protected void setValue(EncodingOption.EncodingOptionBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(EncodingOption.fromValue(value));
    }

    @Override
    protected EncodingOption build(EncodingOption.EncodingOptionBuilder builder) {
        return builder.build();
    }
}
