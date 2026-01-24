package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseBatchItem;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseHeader;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseMessage;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ResponseMessageTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<ResponseMessage, ResponseMessage.ResponseMessageBuilder> {

    public ResponseMessageTtlvDeserializer() {
        super(ResponseMessage.kmipTag);
    }

    @Override
    protected ResponseMessage.ResponseMessageBuilder createBuilder() {
        return ResponseMessage.builder();
    }

    @Override
    protected void setValue(ResponseMessage.ResponseMessageBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RESPONSE_HEADER -> builder.responseHeader(mapper.readValue(p, ResponseHeader.class));
            case KmipTag.Standard.BATCH_ITEM -> {
                try {
                    builder.responseBatchItem(mapper.readValue(p, ResponseBatchItem.class));
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
    protected ResponseMessage build(ResponseMessage.ResponseMessageBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return ResponseMessage.encodingType;
    }
}
