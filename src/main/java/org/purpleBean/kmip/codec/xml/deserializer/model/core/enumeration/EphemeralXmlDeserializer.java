package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Ephemeral;

public class EphemeralXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Ephemeral, String> {

    public EphemeralXmlDeserializer() {
        super(Ephemeral.kmipTag, Ephemeral.encodingType, String.class, value -> new Ephemeral(Ephemeral.fromName(value)));
    }
}