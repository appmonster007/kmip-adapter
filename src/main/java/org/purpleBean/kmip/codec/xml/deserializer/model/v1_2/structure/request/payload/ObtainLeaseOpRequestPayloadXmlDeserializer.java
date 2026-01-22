package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ObtainLeaseOpRequestPayload;

import java.io.IOException;

public class ObtainLeaseOpRequestPayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<ObtainLeaseOpRequestPayload, ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder> {

    public ObtainLeaseOpRequestPayloadXmlDeserializer() {
        super(ObtainLeaseOpRequestPayload.kmipTag);
    }

    @Override
    protected ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder createBuilder() {
        return ObtainLeaseOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ObtainLeaseOpRequestPayload build(ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
