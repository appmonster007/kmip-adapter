package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;

public class DestroyActionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DestroyAction, String> {

    public DestroyActionXmlDeserializer() {
        super(DestroyAction.kmipTag, DestroyAction.encodingType, String.class, value -> DestroyAction.fromName(value).inst());
    }
}