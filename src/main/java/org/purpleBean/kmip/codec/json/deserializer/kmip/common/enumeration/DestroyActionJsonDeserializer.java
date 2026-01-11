package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DestroyAction;

public class DestroyActionJsonDeserializer extends AbstractKmipJsonDeserializer<DestroyAction, String> {

    public DestroyActionJsonDeserializer() {
        super(DestroyAction.kmipTag, DestroyAction.encodingType, String.class, value -> new DestroyAction(DestroyAction.fromName(value)));
    }
}