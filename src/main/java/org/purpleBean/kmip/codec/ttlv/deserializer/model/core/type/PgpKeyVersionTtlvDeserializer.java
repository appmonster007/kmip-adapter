package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;

public class PgpKeyVersionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PgpKeyVersion, Integer> {

    public PgpKeyVersionTtlvDeserializer() {
        super(PgpKeyVersion.kmipTag, PgpKeyVersion.encodingType, Integer.class, value -> PgpKeyVersion.builder().value(value).build());
    }
}