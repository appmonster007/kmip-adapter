package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RngRetrieveOpRequestPayload;

import java.io.IOException;

public class RngRetrieveOpRequestPayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RngRetrieveOpRequestPayload, RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder> {

    public RngRetrieveOpRequestPayloadXmlDeserializer() {
        super(RngRetrieveOpRequestPayload.kmipTag, RngRetrieveOpRequestPayload.encodingType);
    }

    @Override
    protected RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder createBuilder() {
        return RngRetrieveOpRequestPayload.builder();
    }

    @Override
    protected void setValue(RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);

        if (nodeTag.equals(KmipTag.Standard.DATA_LENGTH)) {
            builder.dataLength(ctxt.readValue(p, DataLength.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RngRetrieveOpRequestPayload build(RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
