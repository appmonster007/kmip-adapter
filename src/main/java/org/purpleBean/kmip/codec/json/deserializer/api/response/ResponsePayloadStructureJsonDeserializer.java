package org.purpleBean.kmip.codec.json.deserializer.api.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.io.IOException;

public class ResponsePayloadStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<ResponsePayloadStructure> {

    @Override
    public ResponsePayloadStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return super.deserialize(p, ctxt);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, DeserializationContext ctxt) {
        String ctxtOperation = (String) ctxt.getAttribute("operation");
        Operation.Value operationValue;
        if (ctxtOperation == null) {
            operationValue = null;
        } else {
            operationValue = Operation.fromName(ctxtOperation);
        }
        return ResponsePayloadStructure.getClassFromRegistry(operationValue);
    }
}
