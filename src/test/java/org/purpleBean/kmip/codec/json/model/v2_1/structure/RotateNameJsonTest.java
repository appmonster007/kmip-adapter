package org.purpleBean.kmip.codec.json.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.enumeration.RotateNameType;
import org.purpleBean.kmip.model.v2_1.structure.RotateName;
import org.purpleBean.kmip.model.v2_1.type.RotateNameValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RotateName Json Serialization Tests")
class RotateNameJsonTest extends AbstractJsonSerializationTestSuite<RotateName> {

    @Override
    public Class<RotateName> type() {
        return RotateName.class;
    }

    @Override
    public RotateName createDefault() {
        return RotateName.of(
                RotateNameValue.of("default"),
                RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst());
    }

    @Override
    public RotateName createVariant() {
        return RotateName.of(
                RotateNameValue.of("variant"),
                RotateNameType.Standard.URI.inst());
    }
}