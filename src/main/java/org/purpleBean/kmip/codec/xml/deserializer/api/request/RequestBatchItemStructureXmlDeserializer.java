package org.purpleBean.kmip.codec.xml.deserializer.api.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestBatchItemStructure;
import org.purpleBean.kmip.codec.xml.deserializer.api.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestBatchItem;

import java.io.IOException;

public class RequestBatchItemStructureXmlDeserializer extends KmipDataTypeXmlDeserializer<RequestBatchItemStructure> {

    @Override
    public RequestBatchItemStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return super.deserialize(p, ctxt);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, DeserializationContext ctxt) {
        Class<? extends RequestBatchItemStructure> batchItemClass = RequestBatchItemStructure.getClassFromRegistry();

        if (batchItemClass == null) {
            batchItemClass = SimpleRequestBatchItem.class;
        }

        return batchItemClass;
    }
}