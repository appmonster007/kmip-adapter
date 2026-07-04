package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.CurrentAttribute;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CurrentAttributeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CurrentAttribute, CurrentAttribute.CurrentAttributeBuilder> {

    public CurrentAttributeTtlvDeserializer() {
        super(CurrentAttribute.kmipTag, CurrentAttribute.encodingType);
    }

    @Override
    protected CurrentAttribute.CurrentAttributeBuilder createBuilder() {
        return CurrentAttribute.builder();
    }

    @Override
    protected void setValue(CurrentAttribute.CurrentAttributeBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        // TTLV layout: [tag:3][type:1][length:4][value:n] — type byte is at offset 3
        ByteBuffer view = p.asReadOnlyBuffer();
        view.rewind();
        view.position(3);
        EncodingType childEncodingType = EncodingType.fromTypeValue(view.get())
                .orElseThrow(() -> new IllegalArgumentException("Unknown encoding type in CurrentAttribute child TTLV"));
        Class<? extends KmipDataType> clazz = getKmipDataTypeClass(nodeTag, childEncodingType, mapper);
        builder.attribute((KmipAttribute) mapper.readValue(p, clazz));
    }

    @Override
    protected CurrentAttribute build(CurrentAttribute.CurrentAttributeBuilder builder) {
        return builder.build();
    }
}
