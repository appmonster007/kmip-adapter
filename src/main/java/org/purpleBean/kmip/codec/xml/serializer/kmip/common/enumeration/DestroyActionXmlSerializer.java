package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.DestroyAction;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class DestroyActionXmlSerializer extends AbstractKmipXmlSerializer<DestroyAction, String> {

    public DestroyActionXmlSerializer() {
        super(DestroyAction::getDescription);
    }
}