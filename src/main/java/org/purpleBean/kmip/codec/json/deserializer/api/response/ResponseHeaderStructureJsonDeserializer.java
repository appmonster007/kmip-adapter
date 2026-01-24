package org.purpleBean.kmip.codec.json.deserializer.api.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponseHeaderStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseHeader;

import java.io.IOException;

public class ResponseHeaderStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<ResponseHeaderStructure> {

    @Override
    public ResponseHeaderStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return super.deserialize(p, ctxt);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, DeserializationContext ctxt) {
        Class<? extends ResponseHeaderStructure> headerClass = ResponseHeaderStructure.getClassFromRegistry();
        if (headerClass == null && KmipContext.getSpec().equals(KmipSpec.UnknownVersion)) {
            headerClass = SimpleResponseHeader.class;
        }
        return headerClass;
    }
}
