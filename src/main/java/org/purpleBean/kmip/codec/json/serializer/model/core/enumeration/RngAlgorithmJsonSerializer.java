package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;

public class RngAlgorithmJsonSerializer extends AbstractKmipDataTypeJsonSerializer<RngAlgorithm, String> {

    public RngAlgorithmJsonSerializer() {
        super(RngAlgorithm::getDescription);
    }
}