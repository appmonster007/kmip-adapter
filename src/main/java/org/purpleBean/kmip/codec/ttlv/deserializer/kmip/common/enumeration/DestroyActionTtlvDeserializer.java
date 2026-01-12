package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.DestroyAction;

public class DestroyActionTtlvDeserializer extends AbstractKmipTtlvDeserializer<DestroyAction, Integer> {

    public DestroyActionTtlvDeserializer() {
        super(DestroyAction.kmipTag, DestroyAction.encodingType, Integer.class, value -> new DestroyAction(DestroyAction.fromValue(value)));
    }
}