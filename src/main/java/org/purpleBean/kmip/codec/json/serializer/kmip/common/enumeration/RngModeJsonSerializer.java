package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.RngMode;

public class RngModeJsonSerializer extends AbstractKmipJsonSerializer<RngMode, String> {

    public RngModeJsonSerializer() {
        super(RngMode::getDescription);
    }
}