package org.purpleBean.kmip.codec.json.deserializer.api.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestHeaderStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestHeader;

import java.io.IOException;

public class RequestHeaderStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<RequestHeaderStructure> {

    @Override
    public RequestHeaderStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return super.deserialize(p, ctxt);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, DeserializationContext ctxt) {
        Class<? extends RequestHeaderStructure> headerClass = RequestHeaderStructure.getClassFromRegistry();
        if (headerClass == null && KmipContext.getSpec().equals(KmipSpec.UnknownVersion)) {
            headerClass = SimpleRequestHeader.class;
        }
        return headerClass;
    }
}