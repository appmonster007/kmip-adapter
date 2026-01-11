package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.MaskGenerator;

public class MaskGeneratorXmlDeserializer extends AbstractKmipXmlDeserializer<MaskGenerator, String> {

    public MaskGeneratorXmlDeserializer() {
        super(MaskGenerator.kmipTag, MaskGenerator.encodingType, String.class, value -> new MaskGenerator(MaskGenerator.fromName(value)));
    }
}