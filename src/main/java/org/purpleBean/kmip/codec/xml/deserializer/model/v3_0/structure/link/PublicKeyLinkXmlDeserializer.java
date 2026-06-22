package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.PublicKeyLink;

import java.io.IOException;

public class PublicKeyLinkXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PublicKeyLink, PublicKeyLink.PublicKeyLinkBuilder> {

    public PublicKeyLinkXmlDeserializer() {
        super(PublicKeyLink.kmipTag, PublicKeyLink.encodingType);
    }

    @Override
    protected PublicKeyLink.PublicKeyLinkBuilder createBuilder() {
        return PublicKeyLink.builder();
    }

    @Override
    protected void setValue(PublicKeyLink.PublicKeyLinkBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PublicKeyLink build(PublicKeyLink.PublicKeyLinkBuilder builder) {
        return builder.build();
    }
}
