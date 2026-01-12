package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.MachineIdentifier;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class MachineIdentifierXmlSerializer extends AbstractKmipXmlSerializer<MachineIdentifier, String> {

    public MachineIdentifierXmlSerializer() {
        super(MachineIdentifier::getValue);
    }
}