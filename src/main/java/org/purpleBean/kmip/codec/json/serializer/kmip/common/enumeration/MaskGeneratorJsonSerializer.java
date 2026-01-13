package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.MaskGenerator;

public class MaskGeneratorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MaskGenerator, String> {

    public MaskGeneratorJsonSerializer() {
        super(MaskGenerator::getDescription);
    }
}