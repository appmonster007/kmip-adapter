package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.DestroyAction;

public class DestroyActionJsonSerializer extends AbstractKmipJsonSerializer<DestroyAction, String> {

    public DestroyActionJsonSerializer() {
        super(DestroyAction::getDescription);
    }
}