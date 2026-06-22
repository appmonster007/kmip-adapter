package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.structure.link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.ReplacedObjectLink;

import java.io.IOException;

public class ReplacedObjectLinkJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ReplacedObjectLink, ReplacedObjectLink.ReplacedObjectLinkBuilder> {

    public ReplacedObjectLinkJsonDeserializer() {
        super(ReplacedObjectLink.kmipTag, ReplacedObjectLink.encodingType);
    }

    @Override
    protected ReplacedObjectLink.ReplacedObjectLinkBuilder createBuilder() {
        return ReplacedObjectLink.builder();
    }

    @Override
    protected void setValue(ReplacedObjectLink.ReplacedObjectLinkBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ReplacedObjectLink build(ReplacedObjectLink.ReplacedObjectLinkBuilder builder) {
        return builder.build();
    }
}
