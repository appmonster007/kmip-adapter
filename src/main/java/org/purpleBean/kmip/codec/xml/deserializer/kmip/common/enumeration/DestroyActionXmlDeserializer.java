package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.DestroyAction;

public class DestroyActionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DestroyAction, String> {

    public DestroyActionXmlDeserializer() {
        super(DestroyAction.kmipTag, DestroyAction.encodingType, String.class, value -> new DestroyAction(DestroyAction.fromName(value)));
    }
}