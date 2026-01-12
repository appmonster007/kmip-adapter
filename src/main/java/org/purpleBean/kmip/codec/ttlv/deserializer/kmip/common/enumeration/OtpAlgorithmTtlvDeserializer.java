package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;

public class OtpAlgorithmTtlvDeserializer extends AbstractKmipTtlvDeserializer<OtpAlgorithm, Integer> {

    public OtpAlgorithmTtlvDeserializer() {
        super(OtpAlgorithm.kmipTag, OtpAlgorithm.encodingType, Integer.class, value -> new OtpAlgorithm(OtpAlgorithm.fromValue(value)));
    }
}