package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PgpKey;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;

import java.io.IOException;

public class PgpKeyJsonDeserializer extends AbstractKmipStructureJsonDeserializer<PgpKey, PgpKey.PgpKeyBuilder> {

    public PgpKeyJsonDeserializer() {
        super(PgpKey.kmipTag, PgpKey.encodingType);
    }

    @Override
    protected PgpKey.PgpKeyBuilder createBuilder() {
        return PgpKey.builder();
    }

    @Override
    protected void setValue(PgpKey.PgpKeyBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PGP_KEY_VERSION -> builder.pgpKeyVersion(ctxt.readValue(p, PgpKeyVersion.class));
            case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(ctxt.readValue(p, KeyBlock.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PgpKey build(PgpKey.PgpKeyBuilder builder) {
        return builder.build();
    }
}