package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure.response;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponseBatchItemStructure;
import org.purpleBean.kmip.api.response.ResponseHeaderStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseMessage;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SimpleResponseMessageTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<SimpleResponseMessage, SimpleResponseMessage.SimpleResponseMessageBuilder> {

    public SimpleResponseMessageTtlvDeserializer() {
        super(SimpleResponseMessage.kmipTag);
    }

    @Override
    protected SimpleResponseMessage.SimpleResponseMessageBuilder createBuilder() {
        return SimpleResponseMessage.builder();
    }

    @Override
    protected void setValue(SimpleResponseMessage.SimpleResponseMessageBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RESPONSE_HEADER ->
                    builder.responseHeader(mapper.readValue(p, ResponseHeaderStructure.class));
            case KmipTag.Standard.BATCH_ITEM -> {
                try {
                    builder.responseBatchItem(mapper.readValue(p, ResponseBatchItemStructure.class));
                    builder.responseBatchItemError(null);
                } catch (Exception e) {
                    builder.responseBatchItem(null);
                    builder.responseBatchItemError(e);
                }
            }
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SimpleResponseMessage build(SimpleResponseMessage.SimpleResponseMessageBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return SimpleResponseMessage.encodingType;
    }
}
