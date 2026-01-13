package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.DestroyAction;

public class DestroyActionTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DestroyAction, Integer> {

    public DestroyActionTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}