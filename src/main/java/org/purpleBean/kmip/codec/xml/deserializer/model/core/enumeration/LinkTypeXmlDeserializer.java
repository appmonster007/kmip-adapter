package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.LinkType;

import java.io.IOException;

public class LinkTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<LinkType, LinkType.LinkTypeBuilder> {

    public LinkTypeXmlDeserializer() {
        super(LinkType.kmipTag, LinkType.encodingType);
    }

    @Override
    protected LinkType.LinkTypeBuilder createBuilder() {
        return LinkType.builder();
    }

    @Override
    protected void setValue(LinkType.LinkTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(LinkType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected LinkType build(LinkType.LinkTypeBuilder builder) {
        return builder.build();
    }
}