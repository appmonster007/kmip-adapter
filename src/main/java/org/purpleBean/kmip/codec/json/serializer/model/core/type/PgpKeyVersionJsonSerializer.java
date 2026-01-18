package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;

public class PgpKeyVersionJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PgpKeyVersion, Integer> {

    public PgpKeyVersionJsonSerializer() {
        super(PgpKeyVersion::getValue);
    }
}