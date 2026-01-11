package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.MaskGenerator;

public class MaskGeneratorJsonDeserializer extends AbstractKmipJsonDeserializer<MaskGenerator, String> {

    public MaskGeneratorJsonDeserializer() {
        super(MaskGenerator.kmipTag, MaskGenerator.encodingType, String.class, value -> new MaskGenerator(MaskGenerator.fromName(value)));
    }
}