package org.purpleBean.kmip.codec.xml.deserializer.api.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestMessageStructure;
import org.purpleBean.kmip.codec.xml.deserializer.api.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;

import java.io.IOException;

public class RequestMessageStructureXmlDeserializer extends KmipDataTypeXmlDeserializer<RequestMessageStructure> {

    @Override
    public RequestMessageStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TokenBuffer buffer = new TokenBuffer(p, ctxt);
        buffer.copyCurrentStructure(p);

        KmipSpec previous = KmipContext.getSpec();
        try {
            KmipContext.clear();
            JsonParser replay = buffer.asParser();
            replay.nextToken();
            ctxt.setAttribute("tag", "RequestMessage");
            RequestMessageStructure requestMessage = super.deserialize(replay, ctxt);
            ProtocolVersion protocolVersion = requestMessage.getRequestHeader().getProtocolVersion();

            KmipSpec spec = KmipSpec.fromValue(protocolVersion);
            KmipContext.setSpec(spec);
            JsonParser original = buffer.asParser();
            original.nextToken();
            ctxt.setAttribute("tag", "RequestMessage");
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
        return RequestMessageStructure.getClassFromRegistry();
    }
}
