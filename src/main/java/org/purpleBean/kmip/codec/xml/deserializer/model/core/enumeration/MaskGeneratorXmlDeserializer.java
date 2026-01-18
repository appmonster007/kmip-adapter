package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.MaskGenerator;

public class MaskGeneratorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MaskGenerator, String> {

    public MaskGeneratorXmlDeserializer() {
        super(MaskGenerator.kmipTag, MaskGenerator.encodingType, String.class, value -> new MaskGenerator(MaskGenerator.fromName(value)));
    }
}