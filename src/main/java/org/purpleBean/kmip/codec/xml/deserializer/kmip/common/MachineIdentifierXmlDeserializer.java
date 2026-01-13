package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.MachineIdentifier;

public class MachineIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MachineIdentifier, String> {

    public MachineIdentifierXmlDeserializer() {
        super(MachineIdentifier.kmipTag, MachineIdentifier.encodingType, String.class, value -> MachineIdentifier.builder().value(value).build());
    }
}