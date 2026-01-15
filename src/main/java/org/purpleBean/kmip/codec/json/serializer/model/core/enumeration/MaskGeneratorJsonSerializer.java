package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.MaskGenerator;

public class MaskGeneratorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MaskGenerator, String> {

    public MaskGeneratorJsonSerializer() {
        super(MaskGenerator::getDescription);
    }
}