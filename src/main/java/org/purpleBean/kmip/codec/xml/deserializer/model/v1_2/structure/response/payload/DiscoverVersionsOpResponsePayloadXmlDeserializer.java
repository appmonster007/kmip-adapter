package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DiscoverVersionsOpResponsePayload;

import java.io.IOException;

public class DiscoverVersionsOpResponsePayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DiscoverVersionsOpResponsePayload, DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder> {

    public DiscoverVersionsOpResponsePayloadXmlDeserializer() {
        super(DiscoverVersionsOpResponsePayload.kmipTag, DiscoverVersionsOpResponsePayload.encodingType);
    }

    @Override
    protected DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder createBuilder() {
        return DiscoverVersionsOpResponsePayload.builder();
    }

    @Override
    protected void setValue(DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);

        if (nodeTag.equals(KmipTag.Standard.PROTOCOL_VERSION)) {
            builder.protocolVersion(ctxt.readValue(p, ProtocolVersion.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DiscoverVersionsOpResponsePayload build(DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
