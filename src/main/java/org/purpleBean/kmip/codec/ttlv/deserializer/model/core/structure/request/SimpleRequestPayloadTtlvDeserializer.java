package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure.request;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class SimpleRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<SimpleRequestPayload, SimpleRequestPayload.SimpleRequestPayloadBuilder> {

    public SimpleRequestPayloadTtlvDeserializer() {
        super(SimpleRequestPayload.kmipTag);
    }

    @Override
    protected SimpleRequestPayload.SimpleRequestPayloadBuilder createBuilder() {
        return SimpleRequestPayload.builder();
    }

    @Override
    protected void setValue(SimpleRequestPayload.SimpleRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // No fields to set
    }

    @Override
    protected SimpleRequestPayload build(SimpleRequestPayload.SimpleRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return SimpleRequestPayload.encodingType;
    }

    @Override
    public SimpleRequestPayload deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), SimpleRequestPayload.kmipTag.getTagBytes()) && obj.getType() != getEncodingType().getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s", getEncodingType().getTypeValue(), SimpleRequestPayload.kmipTag.getDescription(), obj.getType()));
        }

        SimpleRequestPayload simpleRequestPayload = SimpleRequestPayload.builder().build();

        if (!simpleRequestPayload.isSupported()) {
            throw new NoSuchElementException();
        }
        return simpleRequestPayload;
    }
}