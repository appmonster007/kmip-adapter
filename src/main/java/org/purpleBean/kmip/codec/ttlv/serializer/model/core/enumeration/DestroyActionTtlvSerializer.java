package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;

public class DestroyActionTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DestroyAction, Integer> {

    public DestroyActionTtlvSerializer() {
        super(DestroyAction::getValue);
    }
}