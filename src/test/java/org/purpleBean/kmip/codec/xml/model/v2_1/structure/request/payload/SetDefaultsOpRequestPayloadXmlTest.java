package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetDefaultsOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.DefaultsInformation;
import org.purpleBean.kmip.model.v2_1.structure.ObjectDefaults;

@DisplayName("SetDefaultsOpRequestPayload Xml Serialization Tests")
class SetDefaultsOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<SetDefaultsOpRequestPayload> {

    @Override
    public Class<SetDefaultsOpRequestPayload> type() {
        return SetDefaultsOpRequestPayload.class;
    }

    @Override
    public SetDefaultsOpRequestPayload createDefault() {
        return SetDefaultsOpRequestPayload.builder().defaultsInformation(DefaultsInformation.of(List.of(ObjectDefaults.builder().objectType(ObjectType.Standard.CERTIFICATE.inst()).attributes(Attributes.of(Collections.emptyList())).build()))).build();
    }

    @Override
    public SetDefaultsOpRequestPayload createVariant() {
        return SetDefaultsOpRequestPayload.builder().defaultsInformation(DefaultsInformation.of(List.of(ObjectDefaults.builder().objectType(ObjectType.Standard.SYMMETRIC_KEY.inst()).attributes(Attributes.of(Collections.emptyList())).build()))).build();
    }
}