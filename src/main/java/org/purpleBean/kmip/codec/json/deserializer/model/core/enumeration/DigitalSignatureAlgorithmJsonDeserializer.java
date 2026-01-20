package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DigitalSignatureAlgorithm, String> {

    public DigitalSignatureAlgorithmJsonDeserializer() {
        super(DigitalSignatureAlgorithm.kmipTag, DigitalSignatureAlgorithm.encodingType, String.class, value -> DigitalSignatureAlgorithm.fromName(value).inst());
    }
}