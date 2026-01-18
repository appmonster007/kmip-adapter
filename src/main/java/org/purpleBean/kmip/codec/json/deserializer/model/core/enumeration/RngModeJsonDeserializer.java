package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RngMode;

public class RngModeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RngMode, String> {

    public RngModeJsonDeserializer() {
        super(RngMode.kmipTag, RngMode.encodingType, String.class, value -> new RngMode(RngMode.fromName(value)));
    }
}