package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.OtpAlgorithm;

public class OtpAlgorithmTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<OtpAlgorithm, Integer> {

    public OtpAlgorithmTtlvDeserializer() {
        super(OtpAlgorithm.kmipTag, OtpAlgorithm.encodingType, Integer.class, value -> new OtpAlgorithm(OtpAlgorithm.fromValue(value)));
    }
}