package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.DeviceCredential;
import org.purpleBean.kmip.model.core.type.*;

import java.io.IOException;

public class DeviceCredentialXmlDeserializer extends AbstractKmipStructureXmlDeserializer<DeviceCredential, DeviceCredential.DeviceCredentialBuilder> {

    public DeviceCredentialXmlDeserializer() {
        super(DeviceCredential.kmipTag);
    }

    @Override
    protected DeviceCredential.DeviceCredentialBuilder createBuilder() {
        return DeviceCredential.builder();
    }

    @Override
    protected void setValue(DeviceCredential.DeviceCredentialBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.DEVICE_SERIAL_NUMBER ->
                    builder.deviceSerialNumber(ctxt.readValue(p, DeviceSerialNumber.class));
            case KmipTag.Standard.PASSWORD -> builder.password(ctxt.readValue(p, Password.class));
            case KmipTag.Standard.DEVICE_IDENTIFIER ->
                    builder.deviceIdentifier(ctxt.readValue(p, DeviceIdentifier.class));
            case KmipTag.Standard.NETWORK_IDENTIFIER ->
                    builder.networkIdentifier(ctxt.readValue(p, NetworkIdentifier.class));
            case KmipTag.Standard.MACHINE_IDENTIFIER ->
                    builder.machineIdentifier(ctxt.readValue(p, MachineIdentifier.class));
            case KmipTag.Standard.MEDIA_IDENTIFIER -> builder.mediaIdentifier(ctxt.readValue(p, MediaIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DeviceCredential build(DeviceCredential.DeviceCredentialBuilder builder) {
        return builder.build();
    }
}