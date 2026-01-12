package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.Ephemeral;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class EphemeralXmlSerializer extends AbstractKmipXmlSerializer<Ephemeral, String> {

    public EphemeralXmlSerializer() {
        super(Ephemeral::getDescription);
    }
}