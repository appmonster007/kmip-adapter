package org.purpleBean.kmip.codec.json.deserializer.api.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponseMessageStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;

import java.io.IOException;

public class ResponseMessageStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<ResponseMessageStructure> {

    @Override
    public ResponseMessageStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TokenBuffer buffer = new TokenBuffer(p, ctxt);
        buffer.copyCurrentStructure(p);

        KmipSpec previous = KmipContext.getSpec();
        try {
            KmipContext.clear();
            JsonParser replay = buffer.asParser();
            replay.nextToken();
            ResponseMessageStructure responseMessage = super.deserialize(replay, ctxt);
            ProtocolVersion protocolVersion = responseMessage.getResponseHeader().getProtocolVersion();

            KmipSpec spec = KmipSpec.fromValue(protocolVersion);
            KmipContext.setSpec(spec);
            JsonParser original = buffer.asParser();
            original.nextToken();
            return super.deserialize(original, ctxt);
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
