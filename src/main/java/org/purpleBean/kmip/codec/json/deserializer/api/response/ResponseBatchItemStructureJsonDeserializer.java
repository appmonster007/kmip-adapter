package org.purpleBean.kmip.codec.json.deserializer.api.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponseBatchItemStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseBatchItem;

import java.io.IOException;

public class ResponseBatchItemStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<ResponseBatchItemStructure> {

    @Override
    public ResponseBatchItemStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return super.deserialize(p, ctxt);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, DeserializationContext ctxt) {
        Class<? extends ResponseBatchItemStructure> batchItemClass = ResponseBatchItemStructure.getClassFromRegistry();
        if (batchItemClass == null && KmipContext.getSpec().equals(KmipSpec.UnknownVersion)) {
            batchItemClass = SimpleResponseBatchItem.class;
        }
        return batchItemClass;
    }
}
