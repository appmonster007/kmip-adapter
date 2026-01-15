package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.Ephemeral;

public class EphemeralXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Ephemeral, String> {

    public EphemeralXmlSerializer() {
        super(Ephemeral::getDescription);
    }
}