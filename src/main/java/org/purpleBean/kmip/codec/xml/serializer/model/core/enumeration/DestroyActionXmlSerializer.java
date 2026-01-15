package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;

public class DestroyActionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DestroyAction, String> {

    public DestroyActionXmlSerializer() {
        super(DestroyAction::getDescription);
    }
}