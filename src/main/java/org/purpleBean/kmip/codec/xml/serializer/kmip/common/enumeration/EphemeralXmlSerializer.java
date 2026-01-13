package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.Ephemeral;

public class EphemeralXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Ephemeral, String> {

    public EphemeralXmlSerializer() {
        super(Ephemeral::getDescription);
    }
}