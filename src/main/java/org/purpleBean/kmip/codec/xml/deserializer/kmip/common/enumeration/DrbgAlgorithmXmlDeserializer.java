package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DrbgAlgorithm, String> {

    public DrbgAlgorithmXmlDeserializer() {
        super(DrbgAlgorithm.kmipTag, DrbgAlgorithm.encodingType, String.class, value -> new DrbgAlgorithm(DrbgAlgorithm.fromName(value)));
    }
}