package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure.request;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class SimpleRequestBatchItemTtlvDeserializer extends KmipDataTypeTtlvDeserializer<SimpleRequestBatchItem> {
    EncodingType type = EncodingType.STRUCTURE;
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.BATCH_ITEM);

    @Override
    public SimpleRequestBatchItem deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", type.getTypeValue(), kmipTag.getDescription()));
        }

        KmipSpec spec = KmipContext.getSpec();
        SimpleRequestBatchItem simpleRequestBatchItem = SimpleRequestBatchItem.builder().build();

        if (!simpleRequestBatchItem.isSupported()) {
            throw new NoSuchElementException();
        }
        return simpleRequestBatchItem;
    }
}
