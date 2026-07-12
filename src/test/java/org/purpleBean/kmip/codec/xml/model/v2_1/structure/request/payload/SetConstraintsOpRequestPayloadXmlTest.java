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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetConstraintsOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;
import org.purpleBean.kmip.model.v2_1.structure.Constraints;

@DisplayName("SetConstraintsOpRequestPayload Xml Serialization Tests")
class SetConstraintsOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<SetConstraintsOpRequestPayload> {

    @Override
    public Class<SetConstraintsOpRequestPayload> type() {
        return SetConstraintsOpRequestPayload.class;
    }

    @Override
    public SetConstraintsOpRequestPayload createDefault() {
        return SetConstraintsOpRequestPayload.builder().constraints(Constraints.of(java.util.List.of())).build();
    }

    @Override
    public SetConstraintsOpRequestPayload createVariant() {
        return SetConstraintsOpRequestPayload.builder().constraints(Constraints.of(java.util.List.of())).build();
    }
}