package org.purpleBean.kmip.codec.json.deserializer.api.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponseMessageStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseMessage;

import java.io.IOException;

public class ResponseMessageStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<ResponseMessageStructure> {

    @Override
    public ResponseMessageStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipSpec previous = KmipContext.getSpec();
        try {
            KmipContext.clear();
            SimpleResponseMessage simpleResponseMessage = ctxt.readValue(p, SimpleResponseMessage.class);
            ProtocolVersion protocolVersion = simpleResponseMessage.getResponseHeader().getProtocolVersion();

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
        return ResponseMessageStructure.getClassFromRegistry();
    }
}
