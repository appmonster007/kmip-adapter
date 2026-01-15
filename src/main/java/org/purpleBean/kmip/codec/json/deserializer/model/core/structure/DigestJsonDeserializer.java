package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.type.DigestValue;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.Digest;

import java.io.IOException;

public class DigestJsonDeserializer extends AbstractKmipStructureJsonDeserializer<Digest, Digest.DigestBuilder> {

    public DigestJsonDeserializer() {
        super(Digest.kmipTag, Digest.encodingType);
    }

    @Override
    protected Digest.DigestBuilder createBuilder() {
        return Digest.builder();
    }

    @Override
    protected void setValue(Digest.DigestBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.HASHING_ALGORITHM ->
                    builder.hashingAlgorithm(ctxt.readValue(p, HashingAlgorithm.class));
            case KmipTag.Standard.DIGEST_VALUE -> builder.digestValue(ctxt.readValue(p, DigestValue.class));
            case KmipTag.Standard.KEY_FORMAT_TYPE -> builder.keyFormatType(ctxt.readValue(p, KeyFormatType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Digest build(Digest.DigestBuilder builder) {
        return builder.build();
    }
}