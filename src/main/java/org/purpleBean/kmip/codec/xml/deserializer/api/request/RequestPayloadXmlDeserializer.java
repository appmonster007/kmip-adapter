package org.purpleBean.kmip.codec.xml.deserializer.api.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayload;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestPayload;

import java.io.IOException;

public class RequestPayloadXmlDeserializer extends KmipDataTypeXmlDeserializer<RequestPayload> {

    @Override
    public RequestPayload deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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

        Class<? extends RequestPayload> payloadClass = RequestPayload.getClassFromRegistry(operationValue);

        if (payloadClass == null) {
            payloadClass = SimpleRequestPayload.class;
        }

        return payloadClass;
    }
}