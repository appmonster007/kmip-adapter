package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure.request;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

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
    }

    @Override
    protected SimpleRequestBatchItem build(SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return SimpleRequestBatchItem.encodingType;
    }

    @Override
    public SimpleRequestBatchItem deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), SimpleRequestBatchItem.kmipTag.getTagBytes()) && obj.getType() != getEncodingType().getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", getEncodingType().getTypeValue(), SimpleRequestBatchItem.kmipTag.getDescription(), obj.getType()));
        }

        SimpleRequestBatchItem simpleRequestBatchItem = SimpleRequestBatchItem.builder().build();

        if (!simpleRequestBatchItem.isSupported()) {
            throw new NoSuchElementException();
        }
        return simpleRequestBatchItem;
    }
}