package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.MaskGenerator;

public class MaskGeneratorJsonSerializer extends AbstractKmipJsonSerializer<MaskGenerator, String> {

    public MaskGeneratorJsonSerializer() {
        super(MaskGenerator::getDescription);
    }
}