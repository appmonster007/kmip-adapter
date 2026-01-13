package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;

public class UnwrapModeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<UnwrapMode, String> {

    public UnwrapModeJsonSerializer() {
        super(UnwrapMode::getDescription);
    }
}