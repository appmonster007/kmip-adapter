package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.MaskGenerator;

public class MaskGeneratorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MaskGenerator, String> {

    public MaskGeneratorJsonDeserializer() {
        super(MaskGenerator.kmipTag, MaskGenerator.encodingType, String.class, value -> new MaskGenerator(MaskGenerator.fromName(value)));
    }
}