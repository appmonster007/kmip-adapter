package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure.request;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class SimpleRequestMessageTtlvDeserializer implements TtlvDeserializer<SimpleRequestMessage> {
    EncodingType type = EncodingType.STRUCTURE;
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.REQUEST_MESSAGE);

    @Override
    public SimpleRequestMessage deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", type.getTypeValue(), kmipTag.getDescription()));
        }

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());

        KmipSpec spec = KmipContext.getSpec();
        SimpleRequestMessage.SimpleRequestMessageBuilder builder = SimpleRequestMessage.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            setValue(builder, nodeTag, ttlvObject, mapper);
        }

        SimpleRequestMessage simpleRequestMessage = builder.build();

        if (!simpleRequestMessage.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return simpleRequestMessage;
    }

    private void setValue(SimpleRequestMessage.SimpleRequestMessageBuilder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.REQUEST_HEADER ->
                    builder.requestHeader(mapper.readValue(ttlvObject.toByteBuffer(), SimpleRequestHeader.class));
            case KmipTag.Standard.BATCH_ITEM -> {
                try {
                    SimpleRequestBatchItem batchItem = mapper.readValue(ttlvObject.toByteBuffer(), SimpleRequestBatchItem.class);
                    builder.requestBatchItem(batchItem)
                            .requestBatchItemError(null);
                } catch (Exception e) {
                    builder.requestBatchItem(null)
                            .requestBatchItemError(e);
                }
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
