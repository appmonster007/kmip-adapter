package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;
import org.purpleBean.kmip.common.enumeration.LinkType;
import org.purpleBean.kmip.common.structure.Link;

import java.io.IOException;

public class LinkXmlDeserializer extends KmipDataTypeXmlDeserializer<Link> {
    private final KmipTag kmipTag = Link.kmipTag;

    @Override
    public Link deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(Link.class, "Invalid Tag for Link");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        Link.LinkBuilder builder = Link.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(Link.class, "Unexpected token: " + p.currentToken());
            }
        }

        Link link = builder.build();

        if (!link.isSupported()) {
            ctxt.reportInputMismatch(Link.class, "Link not supported for spec " + spec);
            return null;
        }

        return link;
    }

    private void setValue(
            Link.LinkBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.LINK_TYPE -> builder.linkType(ctxt.readValue(p, LinkType.class));
            case KmipTag.Standard.LINKED_OBJECT_IDENTIFIER ->
                    builder.linkedObjectIdentifier(ctxt.readValue(p, LinkedObjectIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}