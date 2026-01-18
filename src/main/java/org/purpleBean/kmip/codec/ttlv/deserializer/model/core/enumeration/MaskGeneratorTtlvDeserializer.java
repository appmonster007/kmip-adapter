package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.MaskGenerator;

public class MaskGeneratorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MaskGenerator, Integer> {

    public MaskGeneratorTtlvDeserializer() {
        super(MaskGenerator.kmipTag, MaskGenerator.encodingType, Integer.class, value -> new MaskGenerator(MaskGenerator.fromValue(value)));
    }
}