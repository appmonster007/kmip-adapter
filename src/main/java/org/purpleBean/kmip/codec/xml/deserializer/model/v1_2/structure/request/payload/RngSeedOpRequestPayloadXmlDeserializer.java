package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RngSeedOpRequestPayload;

import java.io.IOException;

public class RngSeedOpRequestPayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<RngSeedOpRequestPayload, RngSeedOpRequestPayload.RngSeedOpRequestPayloadBuilder> {

    public RngSeedOpRequestPayloadXmlDeserializer() {
        super(RngSeedOpRequestPayload.kmipTag);
    }

    @Override
    protected RngSeedOpRequestPayload.RngSeedOpRequestPayloadBuilder createBuilder() {
        return RngSeedOpRequestPayload.builder();
    }

    @Override
    protected void setValue(RngSeedOpRequestPayload.RngSeedOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.DATA)) {
            builder.data(ctxt.readValue(p, DataByteString.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RngSeedOpRequestPayload build(RngSeedOpRequestPayload.RngSeedOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
