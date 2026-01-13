package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.DestroyAction;

public class DestroyActionXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DestroyAction, String> {

    public DestroyActionXmlSerializer() {
        super(DestroyAction::getDescription);
    }
}