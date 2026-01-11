package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.Ephemeral;

public class EphemeralXmlDeserializer extends AbstractKmipXmlDeserializer<Ephemeral, String> {

    public EphemeralXmlDeserializer() {
        super(Ephemeral.kmipTag, Ephemeral.encodingType, String.class, value -> new Ephemeral(Ephemeral.fromName(value)));
    }
}