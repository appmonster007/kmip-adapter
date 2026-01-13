package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.MachineIdentifier;

public class MachineIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MachineIdentifier, String> {

    public MachineIdentifierXmlSerializer() {
        super(MachineIdentifier::getValue);
    }
}