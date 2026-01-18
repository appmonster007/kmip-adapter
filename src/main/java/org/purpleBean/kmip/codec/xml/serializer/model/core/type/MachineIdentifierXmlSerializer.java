package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.MachineIdentifier;

public class MachineIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MachineIdentifier, String> {

    public MachineIdentifierXmlSerializer() {
        super(MachineIdentifier::getValue);
    }
}