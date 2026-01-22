package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.PollOpRequestPayload;

import java.io.IOException;

public class PollOpRequestPayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<PollOpRequestPayload, PollOpRequestPayload.PollOpRequestPayloadBuilder> {

    public PollOpRequestPayloadXmlDeserializer() {
        super(PollOpRequestPayload.kmipTag);
    }

    @Override
    protected PollOpRequestPayload.PollOpRequestPayloadBuilder createBuilder() {
        return PollOpRequestPayload.builder();
    }

    @Override
    protected void setValue(PollOpRequestPayload.PollOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE)) {
            builder.asynchronousCorrelationValue(ctxt.readValue(p, AsynchronousCorrelationValue.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PollOpRequestPayload build(PollOpRequestPayload.PollOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
