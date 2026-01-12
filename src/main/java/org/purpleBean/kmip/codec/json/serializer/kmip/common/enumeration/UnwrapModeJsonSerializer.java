package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;

public class UnwrapModeJsonSerializer extends AbstractKmipJsonSerializer<UnwrapMode, String> {

    public UnwrapModeJsonSerializer() {
        super(UnwrapMode::getDescription);
    }
}