package org.purpleBean.kmip.codec.xml.deserializer.model.v3_0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.DerivationObjectLink;

import java.io.IOException;

public class DerivationObjectLinkXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DerivationObjectLink, DerivationObjectLink.DerivationObjectLinkBuilder> {

    public DerivationObjectLinkXmlDeserializer() {
        super(DerivationObjectLink.kmipTag, DerivationObjectLink.encodingType);
    }

    @Override
    protected DerivationObjectLink.DerivationObjectLinkBuilder createBuilder() {
        return DerivationObjectLink.builder();
    }

    @Override
    protected void setValue(DerivationObjectLink.DerivationObjectLinkBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DerivationObjectLink build(DerivationObjectLink.DerivationObjectLinkBuilder builder) {
        return builder.build();
    }
}
