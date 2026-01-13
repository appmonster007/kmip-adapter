package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DigitalSignatureAlgorithm, String> {

    public DigitalSignatureAlgorithmJsonDeserializer() {
        super(DigitalSignatureAlgorithm.kmipTag, DigitalSignatureAlgorithm.encodingType, String.class, value -> new DigitalSignatureAlgorithm(DigitalSignatureAlgorithm.fromName(value)));
    }
}