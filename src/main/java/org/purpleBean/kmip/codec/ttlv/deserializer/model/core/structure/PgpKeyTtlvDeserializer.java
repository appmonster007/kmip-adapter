package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PgpKey;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PgpKeyTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PgpKey, PgpKey.PgpKeyBuilder> {

    public PgpKeyTtlvDeserializer() {
        super(PgpKey.kmipTag, PgpKey.encodingType);
    }

    @Override
    protected PgpKey.PgpKeyBuilder createBuilder() {
        return PgpKey.builder();
    }

    @Override
    protected void setValue(PgpKey.PgpKeyBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.PGP_KEY_VERSION -> builder.pgpKeyVersion(mapper.readValue(p, PgpKeyVersion.class));
            case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(mapper.readValue(p, KeyBlock.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected PgpKey build(PgpKey.PgpKeyBuilder builder) {
        return builder.build();
    }
}