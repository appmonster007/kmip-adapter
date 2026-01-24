package org.purpleBean.kmip.codec.xml.deserializer.api.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponseMessageStructure;
import org.purpleBean.kmip.codec.xml.deserializer.api.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseMessage;

import java.io.IOException;

public class ResponseMessageStructureXmlDeserializer extends KmipDataTypeXmlDeserializer<ResponseMessageStructure> {

    @Override
    public ResponseMessageStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        SimpleResponseMessage simpleResponseMessage = ctxt.readValue(p, SimpleResponseMessage.class);

        ProtocolVersion protocolVersion = simpleResponseMessage.getResponseHeader().getProtocolVersion();
        KmipSpec previous = KmipContext.getSpec();
        KmipSpec spec = KmipSpec.fromValue(protocolVersion);
        KmipContext.setSpec(spec);
        try {
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
