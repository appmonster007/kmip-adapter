package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.MachineIdentifier;

public class MachineIdentifierXmlSerializer extends AbstractKmipXmlSerializer<MachineIdentifier, String> {

    public MachineIdentifierXmlSerializer() {
        super(MachineIdentifier::getValue);
    }
}