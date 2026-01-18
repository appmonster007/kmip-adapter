package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.GetOpRequestPayload;

import java.io.IOException;

public class GetOpRequestPayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<GetOpRequestPayload, GetOpRequestPayload.GetOpRequestPayloadBuilder> {

    public GetOpRequestPayloadXmlDeserializer() {
        super(GetOpRequestPayload.kmipTag);
    }

    @Override
    protected GetOpRequestPayload.GetOpRequestPayloadBuilder createBuilder() {
        return GetOpRequestPayload.builder();
    }

    @Override
    protected void setValue(GetOpRequestPayload.GetOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.KEY_FORMAT_TYPE -> builder.keyFormatType(ctxt.readValue(p, KeyFormatType.class));
            case KmipTag.Standard.KEY_COMPRESSION_TYPE ->
                    builder.keyCompressionType(ctxt.readValue(p, KeyCompressionType.class));
            case KmipTag.Standard.KEY_WRAPPING_SPECIFICATION ->
                    builder.keyWrappingSpecification(ctxt.readValue(p, KeyWrappingSpecification.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetOpRequestPayload build(GetOpRequestPayload.GetOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}