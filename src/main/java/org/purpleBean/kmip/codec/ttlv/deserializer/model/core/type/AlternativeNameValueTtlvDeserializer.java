package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AlternativeNameValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AlternativeNameValue, AlternativeNameValue.AlternativeNameValueBuilder> {

    public AlternativeNameValueTtlvDeserializer() {
        super(AlternativeNameValue.kmipTag, AlternativeNameValue.encodingType);
    }

    @Override
    protected AlternativeNameValue.AlternativeNameValueBuilder createBuilder() {
        return AlternativeNameValue.builder();
    }

    @Override
    protected void setValue(AlternativeNameValue.AlternativeNameValueBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected AlternativeNameValue build(AlternativeNameValue.AlternativeNameValueBuilder builder) {
        return builder.build();
    }
}