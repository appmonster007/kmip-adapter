package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure.request;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestBatchItem;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SimpleRequestBatchItemTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<SimpleRequestBatchItem, SimpleRequestBatchItem.SimpleRequestBatchItemBuilder> {

    public SimpleRequestBatchItemTtlvDeserializer() {
        super(SimpleRequestBatchItem.kmipTag);
    }

    @Override
    protected SimpleRequestBatchItem.SimpleRequestBatchItemBuilder createBuilder() {
        return SimpleRequestBatchItem.builder();
    }

    @Override
    protected void setValue(SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // This structure is a wrapper, the logic is in the parent deserializer
        builder.requestPayloadStructure(mapper.readValue(p, RequestPayloadStructure.class));
    }

    @Override
    protected SimpleRequestBatchItem build(SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return SimpleRequestBatchItem.encodingType;
    }
}