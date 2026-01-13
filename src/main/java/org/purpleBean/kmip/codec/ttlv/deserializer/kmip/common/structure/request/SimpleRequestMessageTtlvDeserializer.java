package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure.request;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SimpleRequestMessageTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<SimpleRequestMessage, SimpleRequestMessage.SimpleRequestMessageBuilder> {

    public SimpleRequestMessageTtlvDeserializer() {
        super(SimpleRequestMessage.kmipTag);
    }

    @Override
    protected SimpleRequestMessage.SimpleRequestMessageBuilder createBuilder() {
        return SimpleRequestMessage.builder();
    }

    @Override
    protected void setValue(SimpleRequestMessage.SimpleRequestMessageBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.REQUEST_HEADER ->
                    builder.requestHeader(mapper.readValue(p, SimpleRequestHeader.class));
            case KmipTag.Standard.BATCH_ITEM ->
                    builder.requestBatchItem(mapper.readValue(p, SimpleRequestBatchItem.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SimpleRequestMessage build(SimpleRequestMessage.SimpleRequestMessageBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return SimpleRequestMessage.encodingType;
    }
}