package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.MachineIdentifier;

public class MachineIdentifierXmlDeserializer extends AbstractKmipXmlDeserializer<MachineIdentifier, String> {

    public MachineIdentifierXmlDeserializer() {
        super(MachineIdentifier.kmipTag, MachineIdentifier.encodingType, String.class, value -> MachineIdentifier.builder().value(value).build());
    }
}