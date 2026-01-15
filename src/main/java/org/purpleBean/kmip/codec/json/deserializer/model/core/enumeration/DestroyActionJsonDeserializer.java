package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;

public class DestroyActionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DestroyAction, String> {

    public DestroyActionJsonDeserializer() {
        super(DestroyAction.kmipTag, DestroyAction.encodingType, String.class, value -> new DestroyAction(DestroyAction.fromName(value)));
    }
}