package org.purpleBean.kmip.codec.json.deserializer.api.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestMessageStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestMessage;

import java.io.IOException;

public class RequestMessageStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<RequestMessageStructure> {

    @Override
    public RequestMessageStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipSpec previous = KmipContext.getSpec();
        try {
            KmipContext.clear();
            SimpleRequestMessage simpleRequestMessage = ctxt.readValue(p, SimpleRequestMessage.class);
            ProtocolVersion protocolVersion = simpleRequestMessage.getRequestHeader().getProtocolVersion();

            KmipSpec spec = KmipSpec.fromValue(protocolVersion);
            KmipContext.setSpec(spec);
            return super.deserialize(p, ctxt);
        } finally {
            if (previous != null) {
                KmipContext.setSpec(previous);
            } else {
                KmipContext.clear();
            }
        }
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, DeserializationContext ctxt) {
        return RequestMessageStructure.getClassFromRegistry();
    }
}
