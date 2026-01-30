package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.MaximumItems;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MaximumItemsTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MaximumItems, MaximumItems.MaximumItemsBuilder> {

    public MaximumItemsTtlvDeserializer() {
        super(MaximumItems.kmipTag, MaximumItems.encodingType);
    }

    @Override
    protected MaximumItems.MaximumItemsBuilder createBuilder() {
        return MaximumItems.builder();
    }

    @Override
    protected void setValue(MaximumItems.MaximumItemsBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected MaximumItems build(MaximumItems.MaximumItemsBuilder builder) {
        return builder.build();
    }
}
