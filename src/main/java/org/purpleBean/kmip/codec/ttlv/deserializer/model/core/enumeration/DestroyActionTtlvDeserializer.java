package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;

public class DestroyActionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DestroyAction, Integer> {

    public DestroyActionTtlvDeserializer() {
        super(DestroyAction.kmipTag, DestroyAction.encodingType, Integer.class, value -> new DestroyAction(DestroyAction.fromValue(value)));
    }
}