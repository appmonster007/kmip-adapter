package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;

public class RngAlgorithmJsonSerializer extends AbstractKmipDataTypeJsonSerializer<RngAlgorithm, String> {

    public RngAlgorithmJsonSerializer() {
        super(RngAlgorithm::getDescription);
    }
}