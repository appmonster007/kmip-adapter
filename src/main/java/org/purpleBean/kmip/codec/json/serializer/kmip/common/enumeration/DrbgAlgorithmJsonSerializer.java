package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmJsonSerializer extends AbstractKmipJsonSerializer<DrbgAlgorithm, String> {

    public DrbgAlgorithmJsonSerializer() {
        super(DrbgAlgorithm::getDescription);
    }
}