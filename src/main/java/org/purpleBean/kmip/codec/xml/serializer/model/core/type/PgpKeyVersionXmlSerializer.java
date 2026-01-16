package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;

public class PgpKeyVersionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PgpKeyVersion, Integer> {

    public PgpKeyVersionXmlSerializer() {
        super(PgpKeyVersion::getValue);
    }
}