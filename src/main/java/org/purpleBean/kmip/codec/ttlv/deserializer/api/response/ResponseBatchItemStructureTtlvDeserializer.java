package org.purpleBean.kmip.codec.ttlv.deserializer.api.response;

import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponseBatchItemStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseBatchItem;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ResponseBatchItemStructureTtlvDeserializer extends KmipDataTypeTtlvDeserializer<ResponseBatchItemStructure> {

    @Override
    public ResponseBatchItemStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return super.deserialize(ttlvBuffer, mapper);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, TtlvMapper mapper) {
        Class<? extends ResponseBatchItemStructure> batchItemClass = ResponseBatchItemStructure.getClassFromRegistry();
        if (batchItemClass == null && KmipContext.getSpec().equals(KmipSpec.UnknownVersion)) {
            batchItemClass = SimpleResponseBatchItem.class;
        }
        return batchItemClass;
    }
}
