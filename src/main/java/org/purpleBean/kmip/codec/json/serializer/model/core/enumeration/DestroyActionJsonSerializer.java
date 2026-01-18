package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.DestroyAction;

public class DestroyActionJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DestroyAction, String> {

    public DestroyActionJsonSerializer() {
        super(DestroyAction::getDescription);
    }
}