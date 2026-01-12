package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.MaskGenerator;

public class MaskGeneratorXmlSerializer extends AbstractKmipXmlSerializer<MaskGenerator, String> {

    public MaskGeneratorXmlSerializer() {
        super(MaskGenerator::getDescription);
    }
}