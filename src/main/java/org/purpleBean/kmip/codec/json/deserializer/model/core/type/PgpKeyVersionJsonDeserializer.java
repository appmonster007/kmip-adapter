package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;

public class PgpKeyVersionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PgpKeyVersion, Integer> {

    public PgpKeyVersionJsonDeserializer() {
        super(PgpKeyVersion.kmipTag, PgpKeyVersion.encodingType, Integer.class, value -> PgpKeyVersion.builder().value(value).build());
    }
}