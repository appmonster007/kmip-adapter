package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.RngMode;

public class RngModeJsonDeserializer extends AbstractKmipJsonDeserializer<RngMode, String> {

    public RngModeJsonDeserializer() {
        super(RngMode.kmipTag, RngMode.encodingType, String.class, value -> new RngMode(RngMode.fromName(value)));
    }
}