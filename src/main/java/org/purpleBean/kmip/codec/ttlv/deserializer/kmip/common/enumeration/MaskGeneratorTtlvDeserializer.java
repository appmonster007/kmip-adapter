package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.MaskGenerator;

public class MaskGeneratorTtlvDeserializer extends AbstractKmipTtlvDeserializer<MaskGenerator, Integer> {

    public MaskGeneratorTtlvDeserializer() {
        super(MaskGenerator.kmipTag, MaskGenerator.encodingType, Integer.class, value -> new MaskGenerator(MaskGenerator.fromValue(value)));
    }
}