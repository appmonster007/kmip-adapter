package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;

public class PgpKeyVersionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PgpKeyVersion, Integer> {

    public PgpKeyVersionXmlDeserializer() {
        super(PgpKeyVersion.kmipTag, PgpKeyVersion.encodingType, Integer.class, value -> PgpKeyVersion.builder().value(value).build());
    }
}